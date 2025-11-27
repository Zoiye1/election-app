<script setup lang="ts">
// Props
import type { TopNationalParty } from '@/interfaces/IElectionData.ts'
import { ref, watch } from 'vue'
import { NationalPartyService } from '@/services/NationalPartyService.ts'

const props = defineProps<{
  electionId: string
}>()

//TODO: create composables for shared states
// State
const partyResults = ref<TopNationalParty[]>([])
const loading = ref<boolean>(true)
const error = ref<string | null>(null)

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

// watches prop changes, if change is detected refetch data fro correct election.
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
    <template>
      <div class="max-w-4xl mx-auto p-6 bg-white rounded-xl shadow-md">
        <div class="flex items-center justify-between mb-6">
          <h2 class="text-2xl font-bold text-gray-800 flex items-center gap-2">
            📈 Top Partijen Nationaal
          </h2>
        </div>

        <!-- Error message -->
        <div v-if="error" class="bg-red-50 text-red-600 border border-red-200 rounded-lg px-4 py-3 mb-4">
          {{ error }}
        </div>


      </div>
    </template>

  </div>
</template>

