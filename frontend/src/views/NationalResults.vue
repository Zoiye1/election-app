<script setup lang="ts">
import { ElectionService } from '@/services/ElectionService';
import { ref, onMounted } from 'vue';
import Navbar from '@/components/Navbar.vue';
import TotalNationalVotesComponent from '@/components/TotalNationalVotesComponent.vue';
import CandidateResultList from '@/components/CandidateResultList.vue';
import ElectionSelector from '@/components/ElectionSelector.vue';
import { useElection } from '@/composables/useElection';

// Gebruik de shared state uit de composable
const { selectedElection } = useElection();

const totalCounted = ref<number>(0);

onMounted(async () => {
  totalCounted.value = await ElectionService.getTotalVotes();
});
</script>

<template>
  <main>
    <Navbar />
  </main>

  <div class="bg-gradient-to-br from-[#667eea] to-[#764ba2] min-h-screen py-12 pt-24">
    <div class="max-w-7xl mx-auto px-6">
      <!-- Header -->
      <div class="text-center text-white mb-8">
        <h1 class="text-4xl font-bold mb-2">Landelijke Uitslagen</h1>
        <p class="text-xl opacity-90">Tweede Kamerverkiezingen</p>
      </div>

      <!-- Election Selector Component -->
      <div class="mb-8">
        <ElectionSelector />
      </div>

      <!-- Total Votes Card -->
      <div class="max-w-md mx-auto mb-8">
        <TotalNationalVotesComponent :totalCounted="totalCounted" />
      </div>

      <!-- Candidate List -->
      <div class="mt-10">
        <CandidateResultList :electionId="selectedElection" />
      </div>
    </div>
  </div>
</template>
