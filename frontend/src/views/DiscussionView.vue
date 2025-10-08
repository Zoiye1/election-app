<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Navbar from '../components/Navbar.vue'
import DiscussionCard from '../components/DiscussionCard.vue'

interface Discussion {
  id: number
  title: string
  content: string
  author: {
    id: number
    username: string
  }
  createdAt: string
  views: number
  replies: number
}

const discussions = ref<Discussion[]>([])
const loading = ref(true)
const error = ref('')

const fetchDiscussions = async () => {
  try {
    const response = await fetch('http://localhost:8080/discussion')
    if(!response.ok) throw new Error('Failed to fetch discussions')
    discussions.value = await response.json()
  }
  catch (err) {
    error.value = 'Could not load discussions'
    console.error(err)
  }
  finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchDiscussions()
})
</script>

<template>
  <main>
    <Navbar/>
  </main>

  <div class="min-h-screen bg-gray-100">
    <div class="max-w-4xl mx-auto px-4 py-8">
      <!-- Header -->
      <div class="flex justify-between items-center mb-8">
        <h1 class="text-3xl font-bold text-gray-800">Discussies</h1>
        <button
          class="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-6 py-2 rounded-lg flex items-center gap-2 transition-colors">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
            <path d="M8 9a3 3 0 100-6 3 3 0 000 6zM8 11a6 6 0 016 6H2a6 6 0 016-6zM16 7a1 1 0 10-2 0v1h-1a1 1 0 100 2h1v1a1 1 0 102 0v-1h1a1 1 0 100-2h-1V7z" />
          </svg>
          Nieuwe discussie starten
        </button>
      </div>

      <!-- Loading State -->
      <div v-if="loading" class="text-center py-12">
        <p class="text-gray-600">Laden...</p>
      </div>

      <!-- Error State -->
      <div v-else-if="error" class="text-center py-12">
        <p class="text-red-600">{{ error }}</p>
      </div>

      <!-- Empty State -->
      <div v-else-if="discussions.length === 0" class="text-center py-12">
        <p class="text-gray-600">Nog geen discussies. Start de eerste discussie!</p>
      </div>

      <!-- Discussion List -->
      <div v-else class="space-y-6">
        <DiscussionCard
          v-for="discussion in discussions"
          :key="discussion.id"
          :discussion="discussion"
        />
      </div>
    </div>
  </div>
</template>
