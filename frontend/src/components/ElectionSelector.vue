<script setup lang="ts">
import { useElection} from '@/composables/useElection.ts'

// retrieve shared state and function
const { selectedElection, availableElections, setElection } = useElection()
</script>


<template>
<div class="flex justify-center gap-4 flex-wrap">
  <button
    v-for="election in availableElections"
    :key="election.id"
    @click="setElection(election.id)"
    :class="[
        'px-10 py-3 rounded-full text-lg font-semibold cursor-pointer transition-all duration-300',
        'backdrop-blur-lg',
        selectedElection === election.id
          ? 'bg-white text-[#667eea] shadow-lg'
          : 'bg-white/20 text-white hover:bg-white/30 hover:-translate-y-0.5'
      ]"
    >
    {{election.year}}
  </button>
</div>
</template>
