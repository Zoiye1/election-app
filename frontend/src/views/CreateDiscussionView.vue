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
  <div class="min-h-screen bg-gradient-to-br from-indigo-500 via-purple-500 to-purple-600">
    <!-- Navbar at top -->
    <Navbar/>

    <!-- Page content -->
    <main class="max-w-4xl mx-auto px-4 py-10 my-20">
      <!-- Page Header -->
      <div class="text-center mb-10">
        <h1 class="text-4xl md:text-5xl font-bold text-white mb-4">Nieuwe discussie starten</h1>
        <p class="text-white/80 text-lg">Deel je mening over de verkiezingen met anderen</p>
      </div>

      <!-- White card containing the form -->
      <div class="bg-white rounded-2xl shadow-xl p-8">
        <!-- Error Message (only shows if error exists) -->
        <div v-if="error" class="mb-6 p-4 bg-red-50 border-l-4 border-red-400 rounded-lg">
          <div class="flex">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-red-400 mr-3 mt-0.5" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z" clip-rule="evenodd" />
            </svg>
            <p class="text-red-700 font-medium">{{ error }}</p>
          </div>
        </div>

        <!-- The Form -->
        <form @submit.prevent="handleSubmit" class="space-y-6">
          <!-- Title Input Field -->
          <div>
            <label for="title" class="block text-sm font-semibold text-gray-700 mb-3">
              Titel van je discussie *
            </label>
            <input
              id="title"
              v-model="title"
              type="text"
              placeholder="Geef je discussie een duidelijke en interessante titel..."
              class="w-full px-4 py-4 border-2 border-gray-200 rounded-xl focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition-colors text-lg"
              :disabled="loading"
              :class="{ 'opacity-50 cursor-not-allowed': loading }"
            />
            <p class="mt-2 text-sm text-gray-500 flex items-center gap-1">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              Minimaal 5 karakters - maak het aantrekkelijk!
            </p>
          </div>

          <!-- Content Textarea Field -->
          <div>
            <label for="content" class="block text-sm font-semibold text-gray-700 mb-3">
              Beschrijf je standpunt *
            </label>
            <textarea
              id="content"
              v-model="content"
              rows="10"
              placeholder="Schrijf hier uitgebreid je mening, vraag of standpunt. Hoe meer detail, hoe interessanter de discussie wordt..."
              class="w-full px-4 py-4 border-2 border-gray-200 rounded-xl focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition-colors text-lg resize-none"
              :disabled="loading"
              :class="{ 'opacity-50 cursor-not-allowed': loading }"
            ></textarea>
            <p class="mt-2 text-sm text-gray-500 flex items-center gap-1">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
              </svg>
              Minimaal 10 karakters - deel je volledige gedachten
            </p>
          </div>

          <!-- Character counters -->
          <div class="flex justify-between text-sm text-gray-500 bg-gray-50 px-4 py-3 rounded-lg">
            <span>Titel: {{ title.length }} karakters</span>
            <span>Inhoud: {{ content.length }} karakters</span>
          </div>

          <!-- Action Buttons -->
          <div class="flex flex-col sm:flex-row gap-4 pt-6">
            <!-- Cancel Button -->
            <button
              type="button"
              @click="handleCancel"
              :disabled="loading"
              class="flex-1 px-6 py-4 border-2 border-gray-300 rounded-xl text-gray-700 font-semibold hover:bg-gray-50 hover:border-gray-400 disabled:opacity-50 disabled:cursor-not-allowed transition-all duration-300 text-lg">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 inline mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
              </svg>
              Annuleren
            </button>

            <!-- Submit Button -->
            <button
              type="submit"
              :disabled="loading || !title.trim() || !content.trim()"
              class="flex-1 px-6 py-4 bg-gradient-to-r from-indigo-500 to-purple-600 text-white font-semibold rounded-xl hover:from-indigo-600 hover:to-purple-700 hover:-translate-y-0.5 hover:shadow-lg disabled:opacity-50 disabled:cursor-not-allowed disabled:transform-none transition-all duration-300 text-lg">
              <svg v-if="loading" class="animate-spin h-5 w-5 inline mr-2" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              <svg v-else xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 inline mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8" />
              </svg>
              {{ loading ? 'Bezig met opslaan...' : 'Discussie starten' }}
            </button>
          </div>
        </form>

        <!-- Help text -->
        <div class="mt-8 p-4 bg-blue-50 rounded-xl border border-blue-200">
          <h3 class="font-semibold text-blue-900 mb-2 flex items-center gap-2">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            Tips voor een goede discussie
          </h3>
          <ul class="text-sm text-blue-800 space-y-1">
            <li>• Wees respectvol naar andere meningen</li>
            <li>• Gebruik duidelijke argumenten en voorbeelden</li>
            <li>• Stel open vragen om anderen uit te nodigen</li>
            <li>• Deel persoonlijke ervaringen als dat relevant is</li>
          </ul>
        </div>
      </div>
    </main>
  </div>
</template>
