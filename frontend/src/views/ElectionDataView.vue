<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import { onMounted, ref } from 'vue';
import { ElectionService } from '@/services/ElectionService';
import Parties_ConstituencyComponent from '@/components/ConstituencyComponents/Parties_ConstituencyComponent.vue';
import ConstituencyVotesPercentage from '@/components/ConstituencyComponents/ConstituencyVotesPercentage.vue';
import ConstituencyFilter from '@/components/Filters/ConstituencyFilter.vue';
import Navbar from '@/components/Navbar.vue';

const selectedConstituency = ref<string>("Amsterdam");

// Generate particles on mount
onMounted(() => {
  const particlesContainer = document.getElementById('particles')
  if (particlesContainer) {
    for (let i = 0; i < 50; i++) {
      const particle = document.createElement('div')
      particle.className = 'absolute w-1 h-1 bg-white/30 rounded-full'
      particle.style.left = Math.random() * 100 + '%'
      particle.style.top = Math.random() * 100 + '%'
      particle.style.animation = `float ${6 + Math.random() * 3}s ease-in-out infinite`
      particle.style.animationDelay = Math.random() * 6 + 's'
      particlesContainer.appendChild(particle)
    }
  }
})
</script>

<template>
<main>
  <Navbar/>
</main>
  <div class="min-h-screen bg-gradient-to-br from-[#667eea] to-[#764ba2] relative overflow-hidden">
    <div class = "pt-30 ml-250">
      <constituency-filter  v-model="selectedConstituency"/>
    </div>
    <div class = "pt-10 right-10 max-w-md mx-auto">
      <ConstituencyVotesPercentage  :name="selectedConstituency" />
    </div>
    <div class = "pt-20 mb-50 r-50">
      <Parties_ConstituencyComponent :name="selectedConstituency"/>
    </div>
    <div class="fixed inset-0 pointer-events-none" id="particles"></div>
  </div>
</template>
