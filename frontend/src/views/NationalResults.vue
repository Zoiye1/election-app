<script setup lang="ts">
import { ElectionService } from '@/services/ElectionService';
import { ref, onMounted } from 'vue';
import Navbar from '@/components/Navbar.vue';

import TotalNationalVotesComponent from '@/components/TotalNationalVotesComponent.vue';

const selectedElection = ref<string>('TK2025'); // Default on 2025

const totalCounted = ref<number>(0);

onMounted(async () => {
  totalCounted.value = await ElectionService.getTotalVotes();
});
</script>

<template>
  <main>
    <Navbar />
  </main>

  <div class="gradient-background min-h-screen py-12 pt-24">
    <!-- ↑ Voeg pt-24 toe voor extra padding-top -->
    <div class="max-w-7xl mx-auto px-6">
      <!-- Header -->
      <div class="text-center text-white mb-8">
        <h1 class="text-4xl font-bold mb-2">Landelijke Uitslagen</h1>
        <p class="text-xl opacity-90">Tweede Kamerverkiezingen</p>
      </div>

      <div class="year-selector mb-8">
        <button
          @click="selectedElection = 'TK2021'"
          :class="{ active: selectedElection === 'TK2021' }"
          class="year-btn"
        >
          2021
        </button>
        <button
          @click="selectedElection = 'TK2023'"
          :class="{ active: selectedElection === 'TK2023' }"
          class="year-btn"
        >
          2023
        </button>
        <button
          @click="selectedElection = 'TK2025'"
          :class="{ active: selectedElection === 'TK2025' }"
          class="year-btn"
        >
          2025
        </button>
      </div>

      <!-- Total Votes Card -->
      <div class="max-w-md mx-auto">
        <TotalNationalVotesComponent :totalCounted="totalCounted" />
      </div>
    </div>
  </div>


</template>

<style scoped>
.gradient-background {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  position: relative;
  overflow: hidden;
}

.gradient-background::before {
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  background-image:
    radial-gradient(circle, rgba(255, 255, 255, 0.1) 1px, transparent 1px);
  background-size: 50px 50px;
  animation: twinkle 3s infinite;
}

@keyframes twinkle {
  0%, 100% { opacity: 0.3; }
  50% { opacity: 0.6; }
}
</style>
