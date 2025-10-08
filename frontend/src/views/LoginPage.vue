<template>
  <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full bg-white rounded-lg shadow-md p-8">
      <div class="mb-8">
        <h1 class="text-3xl font-bold text-center text-gray-900">Login</h1>
        <p class="mt-2 text-center text-sm text-gray-600">
          Sign in to your account
        </p>
      </div>

      <form @submit.prevent="handleLogin" class="space-y-6">
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
            Password
          </label>
          <input
            type="password"
            id="password"
            v-model="password"
            required
            placeholder="Enter your password"
            class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <button
          type="submit"
          :disabled="isLoading"
          class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 disabled:bg-gray-400 disabled:cursor-not-allowed transition-colors duration-200"
        >
          {{ isLoading ? 'Loading...' : 'Login' }}
        </button>
      </form>

      <div v-if="errorMessage" class="mt-4 p-4 bg-red-100 border border-red-400 text-red-700 rounded-md">
        {{ errorMessage }}
      </div>

      <div v-if="successMessage" class="mt-4 p-4 bg-green-100 border border-green-400 text-green-700 rounded-md">
        {{ successMessage }}
      </div>

      <!-- NIEUW: Link naar registreren -->
      <div class="mt-6 text-center">
        <p class="text-sm text-gray-600">
          Nog geen account?
          <a href="/register" class="font-medium text-blue-600 hover:text-blue-500">
            Registreer nu
          </a>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { LoginService } from '@/services/LoginService';

const router = useRouter();
const loginService = new LoginService();

const email = ref('');
const password = ref('');
const isLoading = ref(false);
const errorMessage = ref('');
const successMessage = ref('');

const handleLogin = async () => {
  isLoading.value = true;
  errorMessage.value = '';
  successMessage.value = '';

  try {
    const result = await loginService.verifyUser(email.value, password.value);

    if (result === true) {
      successMessage.value = 'Login successful!';
      // Redirect naar home na 1 seconde
      setTimeout(() => {
        router.push('/');
      }, 1000);
    } else {
      errorMessage.value = 'Invalid email or password';
    }
  } catch (error) {
    errorMessage.value = 'Something went wrong. Please try again.';
    console.error('Login error:', error);
  } finally {
    isLoading.value = false;
  }
};
</script>
