<script setup lang="ts">

import PartyDetailModal from '@/components/PartyDetailModal.vue'
import type { PartyDetail } from '@/interfaces/IElectionData.ts'
import type { TopNationalParty } from '@/interfaces/IElectionData.ts'
import { ref, watch } from 'vue'
import { NationalPartyService } from '@/services/NationalPartyService.ts'

// Props
const props = defineProps<{
  electionId: string
}>()

//TODO: create composables for shared states
// State
const partyResults = ref<TopNationalParty[]>([])
const loading = ref<boolean>(true)
const error = ref<string | null>(null)

const selectedParty = ref<PartyDetail | null>(null)
const selectedRanking = ref<number>(0)
const showModal = ref<boolean>(false)

async function fetchPartyResults() {
  loading.value = true
  error.value = null

  try {
    // Fetch data from API
    partyResults.value = await NationalPartyService.getTopParties(props.electionId)
  } catch (err) {
    // If it does not succeed display error
    error.value = 'Failed to fetch party results'
    console.error(err)
  } finally {
    loading.value = false
  }
}

async function openPartyDetail(partyId: number, ranking: number) {
  try {
    selectedParty.value = await NationalPartyService.getPartyDetails(props.electionId, partyId)
    selectedRanking.value = ranking
    showModal.value = true
  } catch (err) {
    console.error('Failed to fetch party details:', err)
  }
}

// watches prop changes, if change is detected refetch data for correct election.
watch(
  () => props.electionId,
  () => {
    fetchPartyResults()
  },
  { immediate: true },
)
</script>

<template>
  <div class="max-w-4xl mx-auto p-6 bg-white rounded-xl shadow-md">
    <div class="flex items-center justify-between mb-6">
      <h2 class="text-2xl font-bold text-gray-800 flex items-center gap-2">
        📈 Top Partijen Nationaal
      </h2>
    </div>

    <div v-if="error" class="bg-red-50 text-red-600 border border-red-200 rounded-lg px-4 py-3 mb-4">
      {{ error }}
    </div>

    <div v-if="loading" class="text-center text-gray-500 py-6">Laden...</div>

    <div v-else class="max-h-96 overflow-y-auto space-y-3 pr-2">
      <div
        v-for="(result, i) in partyResults"
        :key="i"
        @click="openPartyDetail(result.partyId, i + 1)"
        class="bg-purple-50 rounded-2xl p-4 flex items-center justify-between hover:bg-purple-100 transition-colors shadow cursor-pointer"
      >
        <div class="flex items-center gap-4">
          <div
            class="w-12 h-12 text-white rounded-full flex items-center justify-center font-bold text-lg flex-shrink-0"
            style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%)"
          >
            {{ i + 1 }}
          </div>
          <div class="text-lg font-bold text-gray-800">
            {{ result.partyName }}
          </div>
        </div>

        <div class="text-right">
          <div class="text-2xl font-bold text-purple-600">
            {{ result.votes.toLocaleString("nl-NL") }}
          </div>
          <div class="text-xs text-gray-400">stemmen</div>
        </div>
      </div>
    </div>

    <PartyDetailModal
      v-if="showModal && selectedParty"
      :party="selectedParty"
      :ranking="selectedRanking"
      @close="showModal = false"
    />

  </div>
</template>
