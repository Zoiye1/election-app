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
      <div class="my-8">
        <div class="bg-white/20 backdrop-blur-md p-5 rounded-2xl shadow-lg max-w-md mx-auto">
          <h2 class="text-white font-semibold text-base mb-3">Selecteer een gemeente</h2>
          <div class="relative">
            <select
              v-model="selectedMunicipality"
              class="w-full px-4 py-2.5 rounded-lg bg-white/90 text-gray-800 font-medium focus:outline-none focus:ring-2 focus:ring-purple-400 appearance-none cursor-pointer shadow-sm hover:bg-white transition-colors"
            >
              <option v-for="municipality in municipalities" :key="municipality" :value="municipality">
                {{ municipality }}
              </option>
            </select>
            <div class="absolute inset-y-0 right-0 flex items-center px-3 pointer-events-none">
              <svg class="w-4 h-4 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </div>
          </div>
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
