<template>
  <div class="min-h-screen bg-gradient-to-br from-[#667eea] to-[#764ba2] relative overflow-hidden flex items-center justify-center py-12 px-4">
    <!-- Animated particles -->
    <div class="fixed inset-0 pointer-events-none" id="particles"></div>

    <!-- Logo -->
    <div class="absolute top-8 left-8 z-10 flex items-center gap-2">
      <div class="w-12 h-12 bg-white/20 backdrop-blur-lg rounded-xl flex items-center justify-center text-white text-xl border-2 border-white/30">
        ✓
      </div>
      <span class="text-2xl font-extrabold text-white">StemSlim</span>
    </div>

    <!-- Verification Container -->
    <div class="relative z-10 w-full max-w-[500px] bg-white rounded-3xl shadow-2xl p-10 sm:p-12 animate-slide-in text-center">
      <!-- Loading State -->
      <div v-if="isVerifying" class="space-y-6">
        <div class="w-20 h-20 mx-auto">
          <svg class="animate-spin h-20 w-20 text-[#667eea]" viewBox="0 0 24 24">
            <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4" fill="none"></circle>
            <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
          </svg>
        </div>
        <h1 class="text-2xl font-bold text-gray-800">Account verifiëren...</h1>
        <p class="text-gray-600">Een moment geduld</p>
      </div>

      <!-- Success State -->
      <div v-if="isVerified && !isVerifying" class="space-y-6">
        <div class="w-20 h-20 mx-auto bg-green-100 rounded-full flex items-center justify-center">
          <span class="text-5xl">✅</span>
        </div>
        <h1 class="text-3xl font-extrabold text-gray-800">Account geverifieerd!</h1>
        <p class="text-gray-600">Je account is succesvol geverifieerd. Je kunt nu inloggen.</p>
        <button
          @click="goToLogin"
          class="w-full py-4 bg-gradient-to-br from-[#667eea] to-[#764ba2] text-white rounded-2xl font-bold text-lg shadow-lg hover:-translate-y-1 transition-all"
        >
          Ga naar login
        </button>
      </div>

      <!-- Error State -->
      <div v-if="errorMessage && !isVerifying" class="space-y-6">
        <div class="w-20 h-20 mx-auto bg-red-100 rounded-full flex items-center justify-center">
          <span class="text-5xl">❌</span>
        </div>
        <h1 class="text-3xl font-extrabold text-gray-800">Verificatie mislukt</h1>
        <p class="text-red-600">{{ errorMessage }}</p>
        <div class="space-y-3">
          <button
            @click="goToLogin"
            class="w-full py-3 bg-gray-200 text-gray-800 rounded-2xl font-semibold hover:bg-gray-300 transition-all"
          >
            Terug naar login
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';

const router = useRouter();
const route = useRoute();

const isVerifying = ref(true);
const isVerified = ref(false);
const errorMessage = ref('');

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

const verifyEmail = async () => {
  const token = route.query.token as string;

  if (!token) {
    errorMessage.value = 'Geen verificatie token gevonden';
    isVerifying.value = false;
    return;
  }

  try {
    const response = await fetch(`${API_BASE_URL}/user/verify-email?token=${token}`, {
      method: 'GET',
      headers: {
        'Accept': 'application/json'
      }
    });

    const data = await response.json();

    if (response.ok && data.success) {
      isVerified.value = true;
    } else {
      errorMessage.value = data.message || 'Verificatie token is verlopen of ongeldig';
    }
  } catch (error) {
    errorMessage.value = 'Er ging iets mis. Probeer het opnieuw.';
    console.error('Verification error:', error);
  } finally {
    isVerifying.value = false;
  }
};

const goToLogin = () => {
  router.push('/login');
};

onMounted(() => {
  verifyEmail();

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
</style>
