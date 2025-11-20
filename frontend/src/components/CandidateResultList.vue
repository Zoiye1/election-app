<script setup lang="ts">
import { ref, watch } from 'vue'
import { getTopCandidatesByElection } from '@/services/candidateResultService'
import type { CandidateResult } from '@/interfaces/candidateResult'

//props
const props = defineProps<{
  electionId: string
}>()

// State
const candidateResults = ref<CandidateResult[]>([])
const loading = ref<boolean>(true)
const error = ref<string | null>(null)

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


//Component with ranking, votes and names displayed.
<template>
  <div class="top-candidates-container">
    <div class="top-candidates-card">
      <div class="header">
        <h2>Top 5 Meest Gestemde Kandidaten</h2>
      </div>

      <div v-if="loading" class="loading">
        <p>Laden...</p>
      </div>

      <div v-else-if="error" class="error">
        {{ error }}
      </div>

      <div v-else class="candidates-list">
        <div
          v-for="(candidate, index) in candidateResults"
          :key="candidate.id"
          class="candidate-row"
        >
          <div class="rank">{{ index + 1 }}</div>

          <div class="info">  <!-- ← Wrapper ROND naam en party -->
            <h3>{{ candidate.fullName }}</h3>
            <p class="party">{{ candidate.party }}</p>
          </div>

          <div class="votes">
            {{ candidate.votes.toLocaleString('nl-NL') }}
            <span class="label">stemmen</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.top-candidates-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px 40px;
}

.top-candidates-card {
  background: white;
  border-radius: 16px;
  padding: 32px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
}

/* Header */
.header {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 24px;
}

.icon {
  width: 32px;
  height: 32px;
  color: #667eea;
}

.header h2 {
  font-size: 24px;
  font-weight: 700;
  color: #1a202c;
  margin: 0;
}

/* Loading */
.loading {
  text-align: center;
  padding: 60px 20px;
  color: #718096;
}

.spinner {
  border: 3px solid #e2e8f0;
  border-top-color: #667eea;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  animation: spin 1s linear infinite;
  margin: 0 auto 16px;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

/* Error */
.error {
  text-align: center;
  padding: 40px;
  color: #e53e3e;
  font-size: 16px;
}

/* Candidates list */
.candidates-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  max-height: 480px;
  overflow-y: auto;
  overflow-x: hidden;
}

.candidate-row {
  display: flex;
  align-items: center;
  background: #f7fafc;
  padding: 20px 24px;
  border-radius: 12px;
  transition: all 0.2s;
}

.candidate-row:hover {
  background: #edf2f7;
  transform: translateX(4px);
}

/* Rank badge */
.rank {
  width: 48px;
  height: 48px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: 700;
  margin-right: 20px;
  flex-shrink: 0;
}

/* Candidate info */
.info {
  flex: 1;
  min-width: 0; /* Voorkomt overflow issues */
}

.info h3 {
  margin: 0 0 4px 0;  /* Ruimte onder naam */
  font-size: 18px;
  font-weight: 600;
  color: #1a202c;
  line-height: 1.2;
}

.party {
  margin: 0;
  padding: 0;
  color: #718096;
  font-size: 14px;
  line-height: 1.4;
}

/* Vote count */
.votes {
  text-align: right;
}

.vote-number {
  display: block;
  font-size: 24px;
  font-weight: 700;
  color: #667eea;
  line-height: 1.2;
}

.label {
  display: block;
  font-size: 12px;
  color: #a0aec0;
  margin-top: 2px;
}

/* Responsive */
@media (max-width: 768px) {
  .top-candidates-card {
    padding: 20px;
  }

  .candidate-row {
    flex-wrap: wrap;
    gap: 12px;
  }

  .rank {
    width: 40px;
    height: 40px;
    font-size: 18px;
  }

  .info {
    flex: 1 1 100%;
    order: 2;
  }

  .votes {
    order: 3;
    margin-left: auto;
  }
}
</style>
