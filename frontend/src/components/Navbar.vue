<template>
  <nav class="sticky top-0 z-50 bg-white/95 backdrop-blur-lg shadow-lg">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4">
      <div class="flex justify-between items-center">
        <!-- Logo -->
        <a href="/" class="flex items-center gap-2 no-underline group">
          <div class="w-10 h-10 bg-gradient-to-br from-[#667eea] to-[#764ba2] rounded-xl flex items-center justify-center text-white text-xl transition-transform group-hover:scale-105">
            ✓
          </div>
          <span class="text-2xl font-extrabold text-[#667eea]">StemSlim</span>
        </a>

        <!-- Desktop Navigation Links -->
        <ul class="hidden md:flex gap-8 list-none items-center">
          <li>
            <a
              href="/"
              class="nav-link relative no-underline text-gray-800 font-semibold transition-colors hover:text-[#667eea]"
            >
              Home
            </a>
          </li>
          <li>
            <a
              href="/electionData"
              class="nav-link relative no-underline text-gray-800 font-semibold transition-colors hover:text-[#667eea]"
            >
              Uitslagen
            </a>
          </li>
          <li>
            <a
              href="/discussion"
              class="nav-link relative no-underline text-gray-800 font-semibold transition-colors hover:text-[#667eea]"
            >
              Discussie
            </a>
          </li>
        </ul>



        <!-- CTA Buttons (Desktop) -->
        <div class="hidden md:flex gap-4">
          <a
            href="/login"
            class="px-6 py-3 rounded-full font-semibold border-2 border-[#667eea] text-[#667eea] transition-all hover:bg-[#667eea] hover:text-white"
          >
            Login
          </a>
          <a
            href="/register"
            class="px-6 py-3 rounded-full font-semibold bg-gradient-to-br from-[#667eea] to-[#764ba2] text-white shadow-lg shadow-[#667eea]/40 transition-all hover:-translate-y-0.5 hover:shadow-xl hover:shadow-[#667eea]/50"
          >
            Registreer
          </a>
        </div>

        <!-- Mobile Menu Button -->
        <button
          @click="mobileMenuOpen = !mobileMenuOpen"
          class="md:hidden text-gray-700 hover:text-[#667eea] focus:outline-none transition-colors"
        >
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path
              v-if="!mobileMenuOpen"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M4 6h16M4 12h16M4 18h16"
            />
            <path
              v-else
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M6 18L18 6M6 6l12 12"
            />
          </svg>
        </button>
      </div>

      <!-- Mobile Menu -->
      <div
        v-show="mobileMenuOpen"
        class="md:hidden mt-4 pb-4 space-y-4 animate-slide-down"
      >
        <!-- Mobile Navigation Links -->
        <div class="space-y-2">
          <a
            href="/"
            class="block text-gray-800 font-semibold py-3 px-4 rounded-xl transition-all hover:bg-[#667eea]/10 hover:text-[#667eea]"
            @click="mobileMenuOpen = false"
          >
            Home
          </a>
          <a
            href="/electionData"
            class="block text-gray-800 font-semibold py-3 px-4 rounded-xl transition-all hover:bg-[#667eea]/10 hover:text-[#667eea]"
            @click="mobileMenuOpen = false"
          >
            Uitslagen
          </a>
          <a
            href="/discussion"
            class="block text-gray-800 font-semibold py-3 px-4 rounded-xl transition-all hover:bg-[#667eea]/10 hover:text-[#667eea]"
            @click="mobileMenuOpen = false"
          >
            Discussie
          </a>
        </div>

        <!-- Mobile CTA Buttons -->
        <div class="space-y-3 pt-2">
          <a
            href="/login"
            class="block text-center text-[#667eea] font-semibold border-2 border-[#667eea] py-3 rounded-full transition-all hover:bg-[#667eea] hover:text-white"
            @click="mobileMenuOpen = false"
          >
            Login
          </a>
          <a
            href="/register"
            class="block text-center text-white font-semibold bg-gradient-to-br from-[#667eea] to-[#764ba2] py-3 rounded-full shadow-lg transition-all hover:-translate-y-0.5"
            @click="mobileMenuOpen = false"
          >
            Registreer
          </a>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'Navbar',
  data() {
    return {
      mobileMenuOpen: false
    }
  }
}
</script>

<style scoped>
/* Animated underline for nav links */
.nav-link::after {
  content: '';
  position: absolute;
  bottom: -5px;
  left: 0;
  width: 0;
  height: 2px;
  background: #667eea;
  transition: width 0.3s ease;
}

.nav-link:hover::after {
  width: 100%;
}

/* Slide down animation for mobile menu */
@keyframes slide-down {
  from {
    opacity: 0;
    transform: translateY(-10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.animate-slide-down {
  animation: slide-down 0.3s ease-out;
}
</style>
