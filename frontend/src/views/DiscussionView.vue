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
          @click="goToCreateDiscussion"
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
        <button
          @click="fetchDiscussions"
          class="mt-4 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
          Opnieuw proberen
        </button>
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
