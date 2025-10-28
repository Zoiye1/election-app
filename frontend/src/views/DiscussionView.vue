<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import Navbar from '../components/Navbar.vue'
import DiscussionCard from '../components/DiscussionCard.vue'
import { DiscussionService, type Discussion } from '@/services/DiscussionService'

// Initialize router and service
const router = useRouter()
const discussionService = new DiscussionService()

// State
const discussions = ref<Discussion[]>([])
const loading = ref(true)
const error = ref('')

// Fetch discussions from API
const fetchDiscussions = async () => {
  loading.value = true
  error.value = ''

  try {
    discussions.value = await discussionService.getAllDiscussions()
  } catch (err) {
    error.value = 'Could not load discussions'
    console.error('Fetch discussions error:', err)
  } finally {
    loading.value = false
  }
}

// Navigate to create discussion page
const goToCreateDiscussion = () => {
  router.push('/create-discussion')
}

// Load discussions when page opens
onMounted(() => {
  fetchDiscussions()
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
  <div class="min-h-screen bg-gradient-to-br from-[#667eea] to-[#764ba2] relative overflow-hidden">
    <div class="fixed inset-0 pointer-events-none" id="particles"></div>
    <!-- Navbar -->
    <Navbar />

    <!-- Main Content -->
    <main class="max-w-6xl mx-auto px-4 py-10 md:py-24">
    <!-- Page Header -->
    <div class="text-center mb-10">
      <h1 class="text-5xl md:text-6xl font-bold text-white mb-6">Discussies</h1>
      <button
        @click="goToCreateDiscussion"
        class="inline-flex items-center gap-3 px-6 py-3 bg-gradient-to-r from-blue-500 to-green-500 text-white font-semibold rounded-full hover:-translate-y-0.5 hover:shadow-xl transition-all duration-300"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="h-5 w-5"
          viewBox="0 0 24 24"
          fill="currentColor"
        >
          <path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6z" />
        </svg>
        Nieuwe discussie starten
      </button>
    </div>

    <!-- Loading State -->
    <div v-if="loading" class="text-center py-16">
      <div class="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-white"></div>
      <p class="text-white text-lg mt-4">Discussies laden...</p>
    </div>

    <!-- Error State -->
    <div v-else-if="error" class="text-center py-16">
      <div class="bg-red-100 border border-red-300 rounded-2xl p-8 max-w-md mx-auto">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="h-12 w-12 text-red-500 mx-auto mb-4"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-2.5L13.732 4c-.77-.833-1.964-.833-2.732 0L3.732 16.5c-.77.833.192 2.5 1.732 2.5z"
          />
        </svg>
        <p class="text-red-700 text-lg mb-4">{{ error }}</p>
        <button
          @click="fetchDiscussions"
          class="px-6 py-3 bg-red-600 text-white font-semibold rounded-full hover:bg-red-700 hover:-translate-y-0.5 transition-all duration-300"
        >
          Opnieuw proberen
        </button>
      </div>
    </div>
    <!-- Discussion List -->
    <div v-else class="space-y-5">
      <DiscussionCard
        v-for="discussion in discussions"
        :key="discussion.id"
        :discussion="discussion"
      />
    </div>
    </main>
  </div>
</template>
