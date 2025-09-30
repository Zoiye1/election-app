<template>
  <nav class="bg-blue-500">
    <div class="max-w-screen-xl mx-auto px-4">
      <div class="flex items-center justify-between h-14">
        <!-- Linker navigatie items -->
        <div class="flex items-center space-x-8">
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

          <!-- Account knop -->
          <a href="#" class="text-white font-medium hover:text-blue-100 transition-colors">
            Account
          </a>
        </div>
      </div>
    </div>
  </nav>
</template>
