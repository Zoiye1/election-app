<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import Navbar from '../components/Navbar.vue'
import { DiscussionService } from '@/services/DiscussionService'

// Router for navigation
const router = useRouter()

// Initialize the service
const discussionService = new DiscussionService()

// Form data - reactive variables that store user input
const title = ref('')
const content = ref('')

// UI state
const loading = ref(false)   // True when submitting
const error = ref('')        // Error message if submission fails

// Validate form before submitting
const isValid = () => {
  // Check if title is empty
  if (!title.value.trim()) {
    error.value = 'Titel is verplicht'
    return false
  }

  // Check if content is empty
  if (!content.value.trim()) {
    error.value = 'Inhoud is verplicht'
    return false
  }

  // Check minimum title length
  if (title.value.length < 5) {
    error.value = 'Titel moet minimaal 5 karakters zijn'
    return false
  }

  // Check minimum content length
  if (content.value.length < 10) {
    error.value = 'Inhoud moet minimaal 10 karakters zijn'
    return false
  }

  // All validation passed
  return true
}

// Submit the form
const handleSubmit = async () => {
  // Clear any previous error messages
  error.value = ''

  // Validate the form inputs
  if (!isValid()) return

  // Start loading state (disables buttons, shows "Bezig...")
  loading.value = true

  try {
    // Use the service to create the discussion
    const createdDiscussion = await discussionService.createDiscussion({
      title: title.value,
      content: content.value,
      author: {
        id: 1
      }
    })

    router.push('/discussion')

  } catch (error) {
    // Handle any errors (network issues, server errors, etc.)
    error.value = 'Er is iets misgegaan. Probeer het opnieuw.'
    console.error('Create discussion error:', error)
  } finally {
    // Always stop loading, whether success or failure
    loading.value = false
  }
}

// Cancel button - go back to discussion list
const handleCancel = () => {
  router.push('/discussion')
}
</script>

<template>
  <!-- Navbar at top -->
  <main>
    <Navbar/>
  </main>

  <!-- Page content -->
  <div class="min-h-screen bg-gray-100">
    <div class="max-w-3xl mx-auto px-4 py-8">

      <!-- Page Header -->
      <div class="mb-8">
        <h1 class="text-3xl font-bold text-gray-900 mb-2">Nieuwe discussie starten</h1>
        <p class="text-gray-600">Deel je mening over de verkiezingen met anderen</p>
      </div>

      <!-- White card containing the form -->
      <div class="bg-white rounded-lg shadow-sm p-6">

        <!-- Error Message (only shows if error exists) -->
        <div v-if="error" class="mb-6 p-4 bg-red-50 border border-red-200 text-red-700 rounded-lg">
          {{ error }}
        </div>

        <!-- The Form -->
        <form @submit.prevent="handleSubmit">

          <!-- Title Input Field -->
          <div class="mb-6">
            <label for="title" class="block text-sm font-medium text-gray-700 mb-2">
              Titel *
            </label>
            <input
              id="title"
              v-model="title"
              type="text"
              placeholder="Geef je discussie een duidelijke titel..."
              class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              :disabled="loading"
            />
            <p class="mt-1 text-sm text-gray-500">Minimaal 5 karakters</p>
          </div>

          <!-- Content Textarea Field -->
          <div class="mb-6">
            <label for="content" class="block text-sm font-medium text-gray-700 mb-2">
              Inhoud *
            </label>
            <textarea
              id="content"
              v-model="content"
              rows="8"
              placeholder="Schrijf hier de inhoud van je discussie..."
              class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent resize-none"
              :disabled="loading"
            ></textarea>
            <p class="mt-1 text-sm text-gray-500">Minimaal 10 karakters</p>
          </div>

          <!-- Action Buttons -->
          <div class="flex justify-end gap-3">
            <!-- Cancel Button -->
            <button
              type="button"
              @click="handleCancel"
              :disabled="loading"
              class="px-6 py-3 border border-gray-300 rounded-lg text-gray-700 font-medium hover:bg-gray-50 disabled:opacity-50 disabled:cursor-not-allowed transition-colors">
              Annuleren
            </button>

            <!-- Submit Button -->
            <button
              type="submit"
              :disabled="loading"
              class="px-6 py-3 bg-blue-600 text-white font-medium rounded-lg hover:bg-blue-700 disabled:opacity-50 disabled:cursor-not-allowed transition-colors">
              {{ loading ? 'Bezig...' : 'Discussie starten' }}
            </button>
          </div>
        </form>

      </div>
    </div>
  </div>
</template>
