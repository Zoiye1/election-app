<template>
  <!-- Background gradient with particles -->
  <div class="min-h-screen bg-gradient-to-br from-[#667eea] to-[#764ba2] relative overflow-hidden flex items-center justify-center py-12 px-4">
    <!-- Animated particles -->
    <div class="fixed inset-0 pointer-events-none" id="particles"></div>

    <!-- Logo in top left -->
    <div class="absolute top-8 left-8 z-10 flex items-center gap-4">
      <a href="/" class="flex items-center gap-2 no-underline group transition-all hover:scale-105 relative">
        <div class="w-12 h-12 bg-white/20 backdrop-blur-lg rounded-xl flex items-center justify-center text-white text-xl border-2 border-white/30 group-hover:border-white/60 transition-all">
          ✓
        </div>
        <span class="text-2xl font-extrabold text-white">StemSlim</span>

        <!-- Tooltip -->
        <div class="absolute -bottom-10 left-0 bg-white text-gray-800 text-xs font-semibold px-3 py-1.5 rounded-lg shadow-lg opacity-0 group-hover:opacity-100 transition-all duration-300 whitespace-nowrap pointer-events-none">
          <span class="flex items-center gap-1">
            🏠 Naar home
          </span>
          <!-- Arrow -->
          <div class="absolute -top-1 left-4 w-2 h-2 bg-white transform rotate-45"></div>
        </div>
      </a>
    </div>

    <!-- Login Container -->
    <div class="relative z-10 w-full max-w-[450px] bg-white rounded-3xl shadow-2xl p-10 sm:p-12 animate-slide-in">
      <!-- Header -->
      <div class="text-center mb-8">
        <h1 class="text-4xl font-extrabold text-gray-800 mb-2">Welkom terug! 👋</h1>
        <p class="text-gray-600">Log in om door te gaan</p>
      </div>

      <!-- Form -->
      <form @submit.prevent="handleLogin" class="space-y-6">
        <!-- Email -->
        <div>
          <label for="email" class="block text-gray-800 font-semibold mb-2 text-sm">
            Email
          </label>
          <input
            type="email"
            id="email"
            v-model="email"
            required
            placeholder="jane@example.com"
            class="w-full px-5 py-4 border-2 border-gray-200 rounded-2xl text-base transition-all focus:outline-none focus:border-[#667eea] focus:shadow-lg focus:shadow-[#667eea]/10"
          />
        </div>

        <!-- Password -->
        <div>
          <label for="password" class="block text-gray-800 font-semibold mb-2 text-sm">
            Wachtwoord
          </label>
          <div class="relative">
            <input
              :type="showPassword ? 'text' : 'password'"
              id="password"
              v-model="password"
              required
              placeholder="Voer je wachtwoord in"
              class="w-full px-5 py-4 pr-12 border-2 border-gray-200 rounded-2xl text-base transition-all focus:outline-none focus:border-[#667eea] focus:shadow-lg focus:shadow-[#667eea]/10"
            />
            <button
              type="button"
              @click="showPassword = !showPassword"
              class="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-[#667eea] transition-colors text-xl"
            >
              {{ showPassword ? '👁️‍🗨️' : '👁️' }}
            </button>
          </div>
          <div class="text-right mt-2">
            <a href="#" class="text-[#667eea] text-sm font-semibold hover:text-[#764ba2] transition-colors">
              Wachtwoord vergeten?
            </a>
          </div>
        </div>

        <!-- Submit Button -->
        <button
          type="submit"
          :disabled="isLoading"
          class="w-full py-4 bg-gradient-to-br from-[#667eea] to-[#764ba2] text-white rounded-2xl font-bold text-lg shadow-lg shadow-[#667eea]/40 transition-all hover:-translate-y-1 hover:shadow-xl hover:shadow-[#667eea]/50 disabled:opacity-50 disabled:cursor-not-allowed disabled:transform-none mt-6"
        >
          <span v-if="!isLoading">Login</span>
          <span v-else class="flex items-center justify-center gap-2">
            <svg class="animate-spin h-5 w-5" viewBox="0 0 24 24">
              <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4" fill="none"></circle>
              <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
            </svg>
            Bezig met inloggen...
          </span>
        </button>
      </form>

      <!-- Error Message -->
      <div v-if="errorMessage"
           class="mt-6 p-4 bg-red-50 border-2 border-red-200 text-red-700 rounded-2xl text-sm">
        ❌ {{ errorMessage }}
      </div>

      <!-- Success Message -->
      <div v-if="successMessage"
           class="mt-6 p-4 bg-green-50 border-2 border-green-200 text-green-700 rounded-2xl text-sm">
        ✅ {{ successMessage }}
      </div>

      <!-- Register Link -->
      <div class="mt-8 text-center pt-6 border-t border-gray-200">
        <p class="text-gray-600">
          Nog geen account?
          <a href="/register" class="text-[#667eea] font-bold hover:text-[#764ba2] transition-colors">
            Registreer nu
          </a>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { LoginService } from '@/services/LoginService';

const router = useRouter();
const loginService = new LoginService();

const email = ref('');
const password = ref('');
const showPassword = ref(false);
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
      successMessage.value = 'Login succesvol! Je wordt doorgestuurd...';
      setTimeout(() => {
        router.push('/');
      }, 1000);
    } else {
      errorMessage.value = 'Ongeldige email of wachtwoord';
    }
  } catch (error: any) {
    if (error.message.includes('EMAIL_NOT_VERIFIED')) {
      errorMessage.value = 'Je account is nog niet geverifieerd. Check je email.';
    } else {
      errorMessage.value = 'Ongeldige email of wachtwoord';
    }
    console.error('Login error:', error);
  } finally {
    isLoading.value = false;
  }
};

onMounted(() => {
  const particlesContainer = document.getElementById('particles');
  if (particlesContainer) {
    for (let i = 0; i < 50; i++) {
      const particle = document.createElement('div');
      particle.className = 'absolute w-1 h-1 bg-white/30 rounded-full animate-float';
      particle.style.left = Math.random() * 100 + '%';
      particle.style.top = Math.random() * 100 + '%';
      particle.style.animationDelay = Math.random() * 6 + 's';
      particlesContainer.appendChild(particle);
    }
  }
});
</script>

<style scoped>
@keyframes float {
  0%, 100% { transform: translateY(0) translateX(0); }
  50% { transform: translateY(-20px) translateX(10px); }
}

.animate-float {
  animation: float 6s ease-in-out infinite;
}

@keyframes slide-in {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.animate-slide-in {
  animation: slide-in 0.5s ease-out;
}

.group:hover .w-12 {
  animation: logo-pulse 0.6s ease-in-out;
}

@keyframes logo-pulse {
  0%, 100% { transform: scale(1); }
  50% { transform: scale(1.1); }
}
</style>
