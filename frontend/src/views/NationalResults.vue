<script setup lang="ts">
import Navbar from '@/components/Navbar.vue';
import TotalNationalVotesComponent from '@/components/TotalNationalVotesComponent.vue';
import CandidateResultList from '@/components/CandidateResultList.vue';
import ElectionSelector from '@/components/ElectionSelector.vue';
import { useElection } from '@/composables/useElection';
import PageNavigator from '@/components/PageNavigator.vue';
import PartyResultList from '@/components/PartyResultList.vue'

// Gebruik de shared state uit de composable
const { selectedElection } = useElection();
</script>

<template>
  <main>
    <Navbar />
  </main>

  <div class="bg-gradient-to-br from-[#667eea] to-[#764ba2] min-h-screen py-12 pt-24">
    <div class="max-w-7xl mx-auto px-6">
      <!-- Header -->
      <div class="text-center text-white mb-8">
      <page-navigator/>
      </div>

      <!-- Election Selector Component -->
      <div class="mb-8">
        <ElectionSelector />
      </div>

      <!-- Total Votes Card -->
      <div class="max-w-md mx-auto mb-8">
        <TotalNationalVotesComponent :electionId="selectedElection" />
      </div>

      <!-- Candidate List & Top Parties side by side -->
      <div class="mt-10 grid grid-cols-1 lg:grid-cols-2 gap-6">
        <CandidateResultList :electionId="selectedElection" />
        <PartyResultList :electionId="selectedElection" />
      </div>

    </div>
  </div>
</template>
