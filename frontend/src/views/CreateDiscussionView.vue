<script setup lang="ts">
import Navbar from '../components/Navbar.vue'
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
