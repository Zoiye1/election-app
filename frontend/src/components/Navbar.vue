<template>
  <nav
    class="fixed top-0 left-0 right-0 z-50 bg-white/95 backdrop-blur-lg transition-all duration-300"
    :class="{ 'shadow-2xl': isScrolled, 'shadow-lg': !isScrolled }"
  >
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4">
      <div class="flex justify-between items-center">
        <!-- Logo -->
        <a href="/" class="flex items-center gap-2 no-underline group">
          <div class="w-10 h-10 logo-gradient rounded-xl flex items-center justify-center text-white text-xl transition-transform group-hover:scale-105">
            ✓
          </div>
          <span class="text-2xl font-extrabold text-purple-600">StemSlim</span>
        </a>

        <!-- Desktop Navigation Links -->
        <ul class="hidden md:flex gap-8 list-none items-center">
          <li>
            <a
              href="/"
              class="nav-link relative no-underline text-gray-800 font-semibold transition-colors hover:text-purple-600"
            >
              Home
            </a>
          </li>
          <li>
            <a
              href="/electionData"
              class="nav-link relative no-underline text-gray-800 font-semibold transition-colors hover:text-purple-600"
            >
              Uitslagen
            </a>
          </li>
          <li>
            <a
              href="/discussion"
              class="nav-link relative no-underline text-gray-800 font-semibold transition-colors hover:text-purple-600"
            >
              Discussie
            </a>
          </li>
        </ul>

        <!-- CTA Buttons (Desktop) -->
        <div class="hidden md:flex gap-4">
          <a
            href="/login"
            class="px-6 py-3 rounded-full font-semibold border-2 border-purple-600 text-purple-600 transition-all hover:bg-purple-600 hover:text-white"
          >
            Login
          </a>
          <a
            href="/register"
            class="px-6 py-3 rounded-full font-semibold button-gradient text-white shadow-lg shadow-purple-400 transition-all hover:-translate-y-0.5 hover:shadow-xl hover:shadow-purple-500"
          >
            Registreer
          </a>
        </div>

        <!-- Mobile Menu Button -->
        <button
          @click="mobileMenuOpen = !mobileMenuOpen"
          class="md:hidden text-gray-700 hover:text-purple-600 focus:outline-none transition-colors"
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
            class="block text-gray-800 font-semibold py-3 px-4 rounded-xl transition-all hover:bg-purple-100 hover:text-purple-600"
            @click="mobileMenuOpen = false"
          >
            Home
          </a>
          <a
            href="/electionData"
            class="block text-gray-800 font-semibold py-3 px-4 rounded-xl transition-all hover:bg-purple-100 hover:text-purple-600"
            @click="mobileMenuOpen = false"
          >
            Uitslagen
          </a>
          <a
            href="/discussion"
            class="block text-gray-800 font-semibold py-3 px-4 rounded-xl transition-all hover:bg-purple-100 hover:text-purple-600"
            @click="mobileMenuOpen = false"
          >
            Discussie
          </a>
        </div>

        <!-- Mobile CTA Buttons -->
        <div class="space-y-3 pt-2">
          <a
            href="/login"
            class="block text-center text-purple-600 font-semibold border-2 border-purple-600 py-3 rounded-full transition-all hover:bg-purple-600 hover:text-white"
            @click="mobileMenuOpen = false"
          >
            Login
          </a>
          <a
            href="/register"
            class="block text-center text-white font-semibold button-gradient py-3 rounded-full shadow-lg transition-all hover:-translate-y-0.5"
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
import { ref, onMounted, onBeforeUnmount } from 'vue'

export default {
  name: 'Navbar',
  setup() {
    const mobileMenuOpen = ref(false)
    const isScrolled = ref(false)

    const handleScroll = () => {
      isScrolled.value = window.scrollY > 20
    }

    onMounted(() => {
      window.addEventListener('scroll', handleScroll)
    })

    onBeforeUnmount(() => {
      window.removeEventListener('scroll', handleScroll)
    })

    return {
      mobileMenuOpen,
      isScrolled
    }
  }
}
</script>

<style scoped>
/* Logo gradient */
.logo-gradient {
  background: linear-gradient(135deg, #667eea, #764ba2);
}

/* Button gradient */
.button-gradient {
  background: linear-gradient(135deg, #667eea, #764ba2);
}

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
