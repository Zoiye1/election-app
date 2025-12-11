<script setup lang="ts">
import { ref, onMounted } from 'vue';
import Navbar from '@/components/Navbar.vue';
import PageNavigator from '@/components/PageNavigator.vue';
import Parties_MunicipalityComponent from '@/components/MunicipalityComponents/Parties_MunicipalityComponent.vue';
import { useElection } from '@/composables/useElection';

const { selectedElection } = useElection();
const selectedMunicipality = ref<string>('Amsterdam');

const municipalities = ref<string[]>([
  'Amsterdam', 'Rotterdam', 'Utrecht', 'Eindhoven',
  'Groningen', 'Tilburg', 'Almere', 'Breda', 'Nijmegen',
  'Enschede', 'Haarlem', 'Arnhem', 'Zaanstad', 'Amersfoort'
]);

onMounted(() => {
  const particlesContainer = document.getElementById('particles');
  if (particlesContainer) {
    for (let i = 0; i < 50; i++) {
      const particle = document.createElement('div');
      particle.className = 'absolute w-1 h-1 bg-white/30 rounded-full';
      particle.style.left = Math.random() * 100 + '%';
      particle.style.top = Math.random() * 100 + '%';
      particle.style.animation = `float ${6 + Math.random() * 3}s ease-in-out infinite`;
      particle.style.animationDelay = Math.random() * 6 + 's';
      particlesContainer.appendChild(particle);
    }
  }
});
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-[#667eea] to-[#764ba2] relative overflow-hidden">
    <Navbar />

    <div class="max-w-5xl mx-auto px-6 pt-24 pb-10">
      <PageNavigator />

      <!-- FILTER -->
      <div class="grid grid-cols-1 gap-8 my-8">
        <div class="bg-white/20 backdrop-blur-md p-6 rounded-2xl shadow-lg">
          <h2 class="text-white font-semibold text-lg mb-4">Selecteer een gemeente</h2>
          <select
            v-model="selectedMunicipality"
            class="w-full px-4 py-2 rounded-lg bg-white/90 text-gray-800 focus:outline-none focus:ring-2 focus:ring-white/50"
          >
            <option v-for="municipality in municipalities" :key="municipality" :value="municipality">
              {{ municipality }}
            </option>
          </select>
        </div>
      </div>

      <!-- PARTIJEN COMPONENT -->
      <div>
        <Parties_MunicipalityComponent :name="selectedMunicipality" :election="selectedElection" />

      </div>
    </div>

    <div class="fixed inset-0 pointer-events-none" id="particles"></div>
  </div>
</template>

<style scoped>
@keyframes float {
  0%, 100% { transform: translateY(0) translateX(0); }
  50% { transform: translateY(-20px) translateX(10px); }
}
</style>
