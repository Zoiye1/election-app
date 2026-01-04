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

    <!-- Register Container -->
    <div class="relative z-10 w-full max-w-[500px] bg-white rounded-3xl shadow-2xl p-10 sm:p-12 animate-slide-in">
      <!-- Header -->
      <div class="text-center mb-8">
        <h1 class="text-4xl font-extrabold text-gray-800 mb-2">Word lid! 🎉</h1>
        <p class="text-gray-600">Maak een account aan en join de discussie</p>
      </div>

      <!-- Form -->
      <form @submit.prevent="handleRegister" class="space-y-6">
        <!-- Username -->
        <div>
          <label for="username" class="block text-gray-800 font-semibold mb-2 text-sm">
            Gebruikersnaam
          </label>
          <input
            type="text"
            id="username"
            v-model="username"
            required
            minlength="3"
            placeholder="janedoe"
            class="w-full px-5 py-4 border-2 border-gray-200 rounded-2xl text-base transition-all focus:outline-none focus:border-[#667eea] focus:shadow-lg focus:shadow-[#667eea]/10"
            :class="{ 'border-red-500': errorMessage && errorMessage.includes('gebruikersnaam') }"
          />
          <p v-if="errorMessage && errorMessage.includes('gebruikersnaam')" class="text-red-500 text-xs mt-2">
            {{ errorMessage }}
          </p>
        </div>

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
            :class="{ 'border-red-500': errorMessage && errorMessage.includes('email') }"
          />
          <p v-if="errorMessage && errorMessage.includes('email')" class="text-red-500 text-xs mt-2">
            {{ errorMessage }}
          </p>
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
              minlength="8"
              placeholder="Minimaal 8 karakters"
              @input="checkPasswordStrength"
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
          <!-- Password Strength Indicator -->
          <div class="h-1 bg-gray-200 rounded-full mt-2 overflow-hidden">
            <div
              class="h-full transition-all duration-300 rounded-full"
              :class="{
                'w-1/3 bg-red-500': passwordStrength === 'weak',
                'w-2/3 bg-orange-500': passwordStrength === 'medium',
                'w-full bg-green-500': passwordStrength === 'strong'
              }"
            ></div>
          </div>
          <p
            class="text-xs mt-1"
            :class="{
              'text-red-500': passwordStrength === 'weak',
              'text-orange-500': passwordStrength === 'medium',
              'text-green-500': passwordStrength === 'strong',
              'text-gray-500': !passwordStrength
            }"
          >
            {{ passwordStrengthText }}
          </p>
        </div>

        <!-- Confirm Password -->
        <div>
          <label for="confirmPassword" class="block text-gray-800 font-semibold mb-2 text-sm">
            Bevestig Wachtwoord
          </label>
          <div class="relative">
            <input
              :type="showConfirmPassword ? 'text' : 'password'"
              id="confirmPassword"
              v-model="confirmPassword"
              required
              placeholder="Herhaal je wachtwoord"
              class="w-full px-5 py-4 pr-12 border-2 border-gray-200 rounded-2xl text-base transition-all focus:outline-none focus:border-[#667eea] focus:shadow-lg focus:shadow-[#667eea]/10"
              :class="{ 'border-red-500': errorMessage && errorMessage.includes('overeen') }"
            />
            <button
              type="button"
              @click="showConfirmPassword = !showConfirmPassword"
              class="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-[#667eea] transition-colors text-xl"
            >
              {{ showConfirmPassword ? '👁️‍🗨️' : '👁️' }}
            </button>
          </div>
          <p v-if="errorMessage && errorMessage.includes('overeen')" class="text-red-500 text-xs mt-2">
            {{ errorMessage }}
          </p>
        </div>

        <!-- Submit Button -->
        <button
          type="submit"
          :disabled="isLoading || !isFormValid"
          class="w-full py-4 bg-gradient-to-br from-[#667eea] to-[#764ba2] text-white rounded-2xl font-bold text-lg shadow-lg shadow-[#667eea]/40 transition-all hover:-translate-y-1 hover:shadow-xl hover:shadow-[#667eea]/50 disabled:opacity-50 disabled:cursor-not-allowed disabled:transform-none relative"
        >
          <span v-if="!isLoading">Registreer</span>
          <span v-else class="flex items-center justify-center gap-2">
            <svg class="animate-spin h-5 w-5" viewBox="0 0 24 24">
              <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4" fill="none"></circle>
              <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
            </svg>
            Bezig met registreren...
          </span>
        </button>
      </form>

      <!-- Error Message -->
      <div v-if="errorMessage && !errorMessage.includes('gebruikersnaam') && !errorMessage.includes('email') && !errorMessage.includes('overeen')"
           class="mt-6 p-4 bg-red-50 border-2 border-red-200 text-red-700 rounded-2xl text-sm">
        ❌ {{ errorMessage }}
      </div>

      <!-- Success Message -->
      <div v-if="successMessage"
           class="mt-6 p-4 bg-green-50 border-2 border-green-200 text-green-700 rounded-2xl text-sm">
        ✅ {{ successMessage }}
      </div>

      <!-- Login Link -->
      <div class="mt-8 text-center pt-6 border-t border-gray-200">
        <p class="text-gray-600">
          Heb je al een account?
          <a href="/login" class="text-[#667eea] font-bold hover:text-[#764ba2] transition-colors">
            Log in
          </a>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { RegisterService } from '@/services/RegisterService';

const router = useRouter();
const registerService = new RegisterService();

const username = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const showPassword = ref(false);
const showConfirmPassword = ref(false);
const isLoading = ref(false);
const errorMessage = ref('');
const successMessage = ref('');
const passwordStrength = ref('');

const passwordStrengthText = computed(() => {
  if (!password.value) return 'Gebruik letters, cijfers en symbolen voor een sterk wachtwoord';
  if (passwordStrength.value === 'weak') return 'Zwak wachtwoord';
  if (passwordStrength.value === 'medium') return 'Gemiddeld wachtwoord';
  if (passwordStrength.value === 'strong') return 'Sterk wachtwoord! 💪';
  return '';
});

const isFormValid = computed(() => {
  return username.value.length >= 3 &&
    email.value.includes('@') &&
    password.value.length >= 8 &&
    password.value === confirmPassword.value;
});

const checkPasswordStrength = () => {
  const pass = password.value;
  let strength = 0;

  if (pass.length >= 8) strength++;
  if (pass.match(/[a-z]/) && pass.match(/[A-Z]/)) strength++;
  if (pass.match(/[0-9]/)) strength++;
  if (pass.match(/[^a-zA-Z0-9]/)) strength++;

  if (strength <= 1) passwordStrength.value = 'weak';
  else if (strength <= 3) passwordStrength.value = 'medium';
  else passwordStrength.value = 'strong';
};

const handleRegister = async () => {
  errorMessage.value = '';
  successMessage.value = '';

  if (password.value !== confirmPassword.value) {
    errorMessage.value = 'Wachtwoorden komen niet overeen';
    return;
  }

  if (password.value.length < 8) {
    errorMessage.value = 'Wachtwoord moet minimaal 8 karakters zijn';
    return;
  }

  if (username.value.length < 3) {
    errorMessage.value = 'Gebruikersnaam moet minimaal 3 karakters zijn';
    return;
  }

  isLoading.value = true;

  try {
    const result = await registerService.registerUser(
      username.value,
      email.value,
      password.value
    );

    successMessage.value = result.message || 'Account aangemaakt! Check je email om je account te verifiëren.';

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
