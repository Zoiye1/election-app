<script setup lang="ts">
import { ref, watch } from 'vue';
import { getTopCandidatesByElection } from '@/services/candidateResultService';
import type { CandidateResult } from '@/interfaces/candidateResult';

//props
const props = defineProps<{
  electionId: string;
}>();

// State
const candidateResults = ref<CandidateResult[]>([]);
const loading = ref<boolean>(true);
const error = ref<string | null>(null);

/**
 * Fetches the top candidates for the selected election from the backend.
 * Updates candidateResults state with the response data.
 * Handles loading and error states during the fetch operation.
 */
async function fetchCandidateResults() {
  loading.value = true;
  error.value = null;

  try {
    candidateResults.value = await getTopCandidatesByElection(props.electionId);
  } catch (err) {
    error.value = 'failed to fetch candidate results';
    console.error(err);
  } finally {
    loading.value = false;
  }
}

// this method watches prop changes and refetches data
watch(() => props.electionId, () => {
  fetchCandidateResults();
}, { immediate: true });



</script>
