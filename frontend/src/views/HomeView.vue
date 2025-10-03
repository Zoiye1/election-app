<script setup lang="ts">
import Navbar from '../components/Navbar.vue'
</script>

<template>
  <main>
    <Navbar/>
  </main>

    <div class="min-h-screen bg-gray-50">
      <!-- Hero Section with picture -->
      <div class="bg-white border-b-4 border-orange-500">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
          <div class="grid md:grid-cols-2 gap-12 items-center">
            <!-- Left Column: Text -->
            <div>
              <h1 class="text-5xl font-bold text-blue-600 mb-6">
                Welkom bij StemSlim!
              </h1>
              <p class="text-xl text-gray-600 mb-4">
                Bekijk op deze site de verkiezingsuitslagen.
              </p>
              <p class="text-xl text-gray-600">
                En begin de discussie!
              </p>
            </div>

            <!-- Right Column: Chart -->
            <div class="flex justify-center">
              <svg viewBox="0 0 400 300" class="w-full max-w-md">
                <!-- Bar Chart -->
                <rect x="50" y="220" width="25" height="40" fill="#2563eb" />
                <rect x="85" y="190" width="25" height="70" fill="#2563eb" />
                <rect x="120" y="170" width="25" height="90" fill="#2563eb" />
                <rect x="155" y="200" width="25" height="60" fill="#2563eb" />
                <rect x="190" y="140" width="25" height="120" fill="#2563eb" />
                <rect x="225" y="170" width="25" height="90" fill="#2563eb" />
                <rect x="260" y="110" width="25" height="150" fill="#2563eb" />
                <rect x="295" y="80" width="25" height="180" fill="#2563eb" />
                <rect x="330" y="50" width="25" height="210" fill="#2563eb" />

                <!-- Base line -->
                <line x1="40" y1="260" x2="370" y2="260" stroke="#2563eb" stroke-width="4" />

                <!-- Trend line -->
                <path d="M 50,240 L 85,220 L 120,210 L 155,200 L 190,180 L 225,160 L 260,130 L 295,90 L 330,40"
                      stroke="#f97316" stroke-width="4" fill="none" stroke-linecap="round" stroke-linejoin="round"/>

                <!-- Arrow -->
                <path d="M 330,40 L 320,45 L 325,50 Z" fill="#f97316" />
                <line x1="330" y1="40" x2="365" y2="5" stroke="#f97316" stroke-width="4" stroke-linecap="round"/>
                <path d="M 365,5 L 355,10 L 360,15 Z" fill="#f97316" />
              </svg>
            </div>
          </div>
        </div>
      </div>

      <!-- Call to Action Cards -->
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
        <div class="grid md:grid-cols-2 gap-8">
          <!-- Discussie Card -->
          <div class="bg-white rounded-lg border-4 border-orange-400 p-8 hover:shadow-lg transition-shadow">
            <h2 class="text-2xl font-bold text-blue-600 mb-4">
              Meedoen aan de discussie?
            </h2>
            <p class="text-gray-600 mb-8">
              Ga dan naar de discussie pagina.
            </p>
            <button
              @click="goToDiscussion"
              class="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-8 py-3 rounded-full transition-colors">
              Bekijk
            </button>
          </div>

          <!-- Result Card -->
          <div class="bg-white rounded
          -lg border-4 border-orange-400 p-8 hover:shadow-lg transition-shadow">
            <h2 class="text-2xl font-bold text-blue-600 mb-4">
              Bekijk hier de stemuitslagen van dit jaar.
            </h2>
            <p class="text-gray-600 mb-8">
              Bekijk dan de Uitslag pagina.
            </p>
            <button
              @click="goToResults"
              class="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-8 py-3 rounded-full transition-colors">
              Bekijk
            </button>
          </div>
        </div>
      </div>
    </div>
</template>
