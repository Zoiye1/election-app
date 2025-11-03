<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import { onMounted } from 'vue';
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';
import Parties_ConstituencyComponent from '@/components/Top10Parties/Parties_ConstituencyComponent.vue';

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
    <div class = "pt-50">
      <Parties_ConstituencyComponent/>
    </div>
    <div class="fixed inset-0 pointer-events-none" id="particles"></div>
  </div>
</template>
