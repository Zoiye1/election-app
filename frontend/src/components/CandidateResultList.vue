<script setup lang="ts">
import { ref, watch } from 'vue'
import { getTopCandidatesByElection } from '@/services/CandidateResultService'
import type { CandidateResult } from '@/interfaces/CandidateResult'
import CandidateDetailModal from '@/components/CandidateDetailModal.vue'

//props
const props = defineProps<{
  electionId: string
}>()

// State
const candidateResults = ref<CandidateResult[]>([])
const loading = ref<boolean>(true)
const error = ref<string | null>(null)
const selectedCandidate = ref<{ candidate: CandidateResult; ranking: number } | null>(null)
/**
 * Fetches the top candidates for the selected election from the backend.
 * Updates candidateResults state with the response data.
 * Handles loading and error states during the fetch operation.
 */
async function fetchCandidateResults() {
  loading.value = true
  error.value = null

  try {
    candidateResults.value = await getTopCandidatesByElection(props.electionId)
  } catch (err) {
    error.value = 'failed to fetch candidate results'
    console.error(err)
  } finally {
    loading.value = false
  }
}

// this method watches prop changes and refetches data
watch(
  () => props.electionId,
  () => {
    fetchCandidateResults()
  },
  { immediate: true },
)
</script>

<template>
  <div class="max-w-4xl mx-auto p-6 bg-white rounded-xl shadow-md">
    <div class="flex items-center justify-between mb-6">
      <h2 class="text-2xl font-bold text-gray-800 flex items-center gap-2">
        🏆 Top 20 Meest Gestemde Kandidaten
      </h2>
    </div>

    <div v-if="loading" class="text-center text-gray-500 py-6">Laden...</div>

    <div v-else-if="error" class="bg-red-50 text-red-600 border border-red-200 rounded-lg px-4 py-3 mb-4">
      {{ error }}
    </div>

    <div v-else class="max-h-96 overflow-y-auto space-y-3 pr-2">
      <div
        v-for="(candidate, index) in candidateResults"
        :key="candidate.id"
        class="bg-purple-50 rounded-2xl p-4 flex items-center justify-between hover:bg-purple-100 transition-colors shadow"
      >
        <div class="flex items-center gap-4">
          <div
            class="w-12 h-12 text-white rounded-full flex items-center justify-center font-bold text-lg flex-shrink-0"
            style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%)"
          >
            {{ index + 1 }}
          </div>
          <div>
            <div class="text-lg font-bold text-gray-800">
              {{ candidate.fullName }}
            </div>
            <div class="text-sm text-gray-500">
              {{ candidate.party }}
            </div>
          </div>
        </div>

        <div class="text-right">
          <div class="text-2xl font-bold text-purple-600">
            {{ candidate.votes.toLocaleString('nl-NL') }}
          </div>
          <div class="text-xs text-gray-400">stemmen</div>
        </div>
      </div>
    </div>
  </div>
</template>
