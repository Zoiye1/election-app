<template>
  <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full bg-white rounded-lg shadow-md p-8">
      <div class="mb-8">
        <h1 class="text-3xl font-bold text-center text-gray-900">Registreer</h1>
        <p class="mt-2 text-center text-sm text-gray-600">
          Maak een nieuw account aan
        </p>
      </div>

      <form @submit.prevent="handleRegister" class="space-y-6">
        <div>
          <label for="username" class="block text-sm font-medium text-gray-700 mb-2">
            Gebruikersnaam
          </label>
          <input
            type="text"
            id="username"
            v-model="username"
            required
            placeholder="janedoe"
            class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <div>
          <label for="email" class="block text-sm font-medium text-gray-700 mb-2">
            Email
          </label>
          <input
            type="email"
            id="email"
            v-model="email"
            required
            placeholder="jane@example.com"
            class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <div>
          <label for="password" class="block text-sm font-medium text-gray-700 mb-2">
            Wachtwoord
          </label>
          <input
            type="password"
            id="password"
            v-model="password"
            required
            placeholder="Minimaal 8 karakters"
            minlength="8"
            class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <div>
          <label for="confirmPassword" class="block text-sm font-medium text-gray-700 mb-2">
            Bevestig Wachtwoord
          </label>
          <input
            type="password"
            id="confirmPassword"
            v-model="confirmPassword"
            required
            placeholder="Herhaal je wachtwoord"
            class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <button
          type="submit"
          :disabled="isLoading"
          class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 disabled:bg-gray-400 disabled:cursor-not-allowed transition-colors duration-200"
        >
          {{ isLoading ? 'Bezig met registreren...' : 'Registreer' }}
        </button>
      </form>

      <div v-if="errorMessage" class="mt-4 p-4 bg-red-100 border border-red-400 text-red-700 rounded-md">
        {{ errorMessage }}
      </div>

      <div v-if="successMessage" class="mt-4 p-4 bg-green-100 border border-green-400 text-green-700 rounded-md">
        {{ successMessage }}
      </div>

      <div class="mt-6 text-center">
        <p class="text-sm text-gray-600">
          Heb je al een account?
          <a href="/login" class="font-medium text-blue-600 hover:text-blue-500">
            Log in
          </a>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { RegisterService } from '@/services/RegisterService';

const router = useRouter();
const registerService = new RegisterService();

const username = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const isLoading = ref(false);
const errorMessage = ref('');
const successMessage = ref('');

const handleRegister = async () => {
  // Reset messages
  errorMessage.value = '';
  successMessage.value = '';

  // Validatie
  if (password.value !== confirmPassword.value) {
    errorMessage.value = 'Wachtwoorden komen niet overeen';
    return;
  }

  if (password.value.length < 8) {
    errorMessage.value = 'Wachtwoord moet minimaal 8 karakters zijn';
    return;
  }

  isLoading.value = true;

  try {
    const result = await registerService.registerUser(
      username.value,
      email.value,
      password.value
    );

    successMessage.value = 'Account succesvol aangemaakt! Je wordt doorgestuurd naar de login pagina...';

    // Redirect naar login na 2 seconden
    setTimeout(() => {
      router.push('/login');
    }, 2000);

  } catch (error: any) {
    if (error.message.includes('409') || error.message.includes('Conflict')) {
      errorMessage.value = 'Deze email of gebruikersnaam bestaat al';
    } else {
      errorMessage.value = 'Er ging iets mis. Probeer het opnieuw.';
    }
    console.error('Register error:', error);
  } finally {
    isLoading.value = false;
  }
};
</script>
