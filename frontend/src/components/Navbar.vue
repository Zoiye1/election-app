<template>
  <div>
    <div class="bg-white h-24 flex items-center px-4 md:px-8">
      <div class="flex items-center justify-between w-full">

        <img src="../assets/logo.png" alt="StemSlim Logo" class="h-12 md:h-16" />

        <!-- Hamburger menu mobile only -->
        <button
          @click="mobileMenuOpen = !mobileMenuOpen"
          class="md:hidden text-gray-700 hover:text-gray-900 focus:outline-none"
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
    </div>


    <nav class="bg-blue-500">
      <!-- Desktop navigation-->
      <div class="hidden md:block max-w-screen-xl mx-auto px-8">
        <div class="flex items-center justify-between h-14">
          <!-- This is for to navigate the items from postion -->
          <div class="flex items-center space-x-8 -ml-4">
            <a href="#" class="text-white font-medium hover:text-blue-100 transition-colors">
              Home
            </a>
            <a href="#" class="text-white font-medium hover:text-blue-100 transition-colors">
              Uitslagen
            </a>
            <a href="#" class="text-white font-medium hover:text-blue-100 transition-colors">
              Discussie
            </a>
          </div>

          <!-- Rechter sectie met search en account -->
          <div class="flex items-center space-x-4">
            <!-- Search bar -->
            <div class="relative">
              <input
                type="text"
                placeholder="Search..."
                class="w-64 px-4 py-1.5 pr-10 rounded-full text-sm text-gray-700 bg-white focus:outline-none focus:ring-2 focus:ring-blue-300"
              />
              <button class="absolute right-2 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
                </svg>
              </button>
            </div>

            <!-- Account button -->
            <a href="#" class="text-white font-medium hover:text-blue-100 transition-colors">
              Account
            </a>
          </div>
        </div>
      </div>

      <!-- Mobile navigation menu -->
      <div
        v-show="mobileMenuOpen"
        class="md:hidden bg-blue-500 border-t border-blue-400"
      >
        <div class="px-4 py-3 space-y-3">
          <a
            href="#"
            class="block text-white font-medium hover:text-blue-100 py-2 transition-colors"
            @click="mobileMenuOpen = false"
          >
            Home
          </a>
          <a
            href="#"
            class="block text-white font-medium hover:text-blue-100 py-2 transition-colors"
            @click="mobileMenuOpen = false"
          >
            Uitslagen
          </a>
          <a
            href="#"
            class="block text-white font-medium hover:text-blue-100 py-2 transition-colors"
            @click="mobileMenuOpen = false"
          >
            Discussie
          </a>

          <!-- Search bar mobiel -->
          <div class="relative pt-2">
            <input
              type="text"
              placeholder="Search..."
              class="w-full px-4 py-2 pr-10 rounded-full text-sm text-gray-700 bg-white focus:outline-none focus:ring-2 focus:ring-blue-300"
            />
            <button class="absolute right-2 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
              </svg>
            </button>
          </div>

          <!-- Account button mobile -->
          <a
            href="#"
            class="block text-white font-medium hover:text-blue-100 py-2 transition-colors"
            @click="mobileMenuOpen = false"
          >
            Account
          </a>
        </div>
      </div>
    </nav>
  </div>
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
