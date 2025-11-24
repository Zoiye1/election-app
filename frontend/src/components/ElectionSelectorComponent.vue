<script setup lang="ts">
import { useElection} from '@/composables/useElection.ts'

// retrieve shared state and function
const { selectedElection, availableElections, setElection } = useElection()
</script>


<template>
<div class="election-selector">
  <button
    v-for="election in availableElections"
    :key="election.id"
    @click="setElection(election.id)"
    :class="{ active: election.id === selectedElection.id }"
    class="election-btn"
    >
    {{election.year}}
  </button>
</div>
</template>
