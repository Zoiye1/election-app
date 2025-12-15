<script setup lang="ts">
import { computed, defineProps, onMounted, ref, watch } from 'vue'
import { ElectionService } from '@/services/ElectionService.ts'

const props = defineProps<{
  electionId: string
}>()

const totalCounted = ref<number>(0)

async function fetchTotalVotes() {
  totalCounted.value = await ElectionService.getTotalVotes(props.electionId)
}

watch(
  () => props.electionId,
  () => {
    fetchTotalVotes()
  },
  { immediate: true },
)

const formattedVotes = computed(() => {
  return totalCounted.value.toLocaleString('nl-NL')
})


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
  <div class="bg-white rounded-2xl shadow-md p-6 flex items-center gap-4">
    <!-- Icon -->
    <div class="bg-purple-100 rounded-xl p-3 flex-shrink-0">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        class="h-8 w-8 text-purple-600"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"
        />
      </svg>
    </div>

    <!-- Content -->
    <div class="flex-1">
      <p class="text-gray-600 font-medium text-sm mb-1">Totaal Stemmen</p>
      <p class="text-3xl font-bold text-gray-800">{{ formattedVotes }}</p>
    </div>
    <div class="fixed inset-0 pointer-events-none" id="particles"></div>
  </div>
</template>

<style scoped></style>
