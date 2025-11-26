<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import { onMounted, ref } from 'vue';
import Navbar from '@/components/Navbar.vue';
import ElectionSelector from '@/components/ElectionSelector.vue';
import { useElection } from '@/composables/useElection';
import PageNavigator from '@/components/PageNavigator.vue';

const selectedConstituency = ref<string>("Amsterdam");
const { selectedElection } = useElection();

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
    <Navbar />
  </main>

  <<div class="bg-gradient-to-br from-[#667eea] to-[#764ba2] min-h-screen py-12 pt-24">
  <div class="max-w-7xl mx-auto px-6">
    <!-- Header -->
    <div class="text-center text-white mb-8">
      <page-navigator/>
    </div>

    <!-- Election Selector Component -->
    <div class="mb-8">
      <ElectionSelector />
    </div>
  </div>
  <div class="fixed inset-0 pointer-events-none" id="particles"></div>
  <h1 class = "text-center text-xl text-white mt-10">We werken momenteel aan deze pagina. kom later terug ;)</h1>
</div>
</template>

