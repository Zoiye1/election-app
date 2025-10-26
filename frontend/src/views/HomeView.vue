<script setup lang="ts">
import {onMounted} from 'vue'
import {useRouter} from 'vue-router'
import Navbar from '../components/Navbar.vue'
// import Footer from '../components/Footer.vue'

const router = useRouter()

const goToDiscussion = () => {
  router.push('/discussion')
}

const goToResults = () => {
  router.push('/electionData')
}

// Generate particles on mount
onMounted(() => {
  const particlesContainer = document.getElementById('particles')
  if (particlesContainer) {
    for (let i = 0; i < 50; i++) {
      const particle = document.createElement('div')
      particle.className = 'absolute w-1 h-1 bg-white/30 rounded-full'
      particle.style.left = Math.random() * 100 + '%'
      particle.style.top = Math.random() * 100 + '%'
      particle.style.animation = `float ${6 + Math.random() * 3}s ease-in-out infinite`
      particle.style.animationDelay = Math.random() * 6 + 's'
      particlesContainer.appendChild(particle)
    }
  }
})
</script>

<template>
  <main>
    <Navbar/>
  </main>

  <!-- Background gradient with particles -->
  <div class="min-h-screen bg-gradient-to-br from-[#667eea] to-[#764ba2] relative overflow-hidden">
    <!-- Animated particles -->
    <div class="fixed inset-0 pointer-events-none" id="particles"></div>

    <!-- Hero Section -->
    <div class="relative z-10 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16 md:py-24">
      <div class="grid md:grid-cols-2 gap-12 items-center">
        <!-- Left Column: Text -->
        <div class="space-y-6">
          <!-- Live Indicator -->
          <!--          <div class="inline-flex items-center gap-2 bg-white/20 px-4 py-2 rounded-full text-white text-sm backdrop-blur-lg">-->
          <!--            <span class="w-2.5 h-2.5 bg-red-500 rounded-full animate-pulse"></span>-->
          <!--            <span>Live verkiezingsresultaten</span>-->
          <!--          </div>-->

          <h1 class="text-5xl md:text-6xl font-extrabold text-white leading-tight">
            Jouw stem,<br>
            <span
              class="bg-gradient-to-r from-[#ffd89b] to-[#19547b] bg-clip-text text-transparent">
              jouw toekomst!
            </span>
          </h1>

          <p class="text-xl md:text-2xl text-white/90 leading-relaxed">
            Ontdek verkiezingsresultaten, doe mee aan discussies en
            maak impact met jouw mening. StemSlim maakt politiek relevant en toegankelijk.
          </p>

          <!-- CTA Buttons -->
          <div class="flex flex-wrap gap-4 pt-4">
            <button
              @click="goToResults"
              class="px-8 py-4 bg-white text-[#667eea] rounded-full font-bold text-lg shadow-2xl transition-all hover:-translate-y-1 hover:shadow-3xl">
              🗳️ Bekijk Uitslagen
            </button>
            <button
              @click="goToDiscussion"
              class="px-8 py-4 bg-white/20 text-white border-2 border-white rounded-full font-bold text-lg backdrop-blur-lg transition-all hover:bg-white/30">
              💬 Start Discussie
            </button>
          </div>


        </div>

        <!-- Right Column: Chart -->
        <div class="relative">
          <div
            class="relative w-full bg-white/10 backdrop-blur-lg rounded-3xl p-8 border-2 border-white/20 shadow-2xl">
            <h3 class="text-white mb-4 text-lg font-semibold">📊 Verkiezingstrends 2025</h3>

            <!-- Interactive Chart -->
            <div class="relative h-80 flex items-end justify-around gap-2">
              <div class="bar-wrapper w-full flex flex-col items-center">
                <div
                  class="bar w-full bg-gradient-to-t from-white/30 to-white/80 rounded-t-xl transition-all duration-300 hover:from-yellow-400/50 hover:to-yellow-400/90 hover:scale-105 cursor-pointer animate-grow-bar"
                  style="height: 40%; animation-delay: 0.1s;"></div>
              </div>
              <div class="bar-wrapper w-full flex flex-col items-center">
                <div
                  class="bar w-full bg-gradient-to-t from-white/30 to-white/80 rounded-t-xl transition-all duration-300 hover:from-yellow-400/50 hover:to-yellow-400/90 hover:scale-105 cursor-pointer animate-grow-bar"
                  style="height: 55%; animation-delay: 0.2s;"></div>
              </div>
              <div class="bar-wrapper w-full flex flex-col items-center">
                <div
                  class="bar w-full bg-gradient-to-t from-white/30 to-white/80 rounded-t-xl transition-all duration-300 hover:from-yellow-400/50 hover:to-yellow-400/90 hover:scale-105 cursor-pointer animate-grow-bar"
                  style="height: 48%; animation-delay: 0.3s;"></div>
              </div>
              <div class="bar-wrapper w-full flex flex-col items-center">
                <div
                  class="bar w-full bg-gradient-to-t from-white/30 to-white/80 rounded-t-xl transition-all duration-300 hover:from-yellow-400/50 hover:to-yellow-400/90 hover:scale-105 cursor-pointer animate-grow-bar"
                  style="height: 72%; animation-delay: 0.4s;"></div>
              </div>
              <div class="bar-wrapper w-full flex flex-col items-center">
                <div
                  class="bar w-full bg-gradient-to-t from-white/30 to-white/80 rounded-t-xl transition-all duration-300 hover:from-yellow-400/50 hover:to-yellow-400/90 hover:scale-105 cursor-pointer animate-grow-bar"
                  style="height: 63%; animation-delay: 0.5s;"></div>
              </div>
              <div class="bar-wrapper w-full flex flex-col items-center">
                <div
                  class="bar w-full bg-gradient-to-t from-white/30 to-white/80 rounded-t-xl transition-all duration-300 hover:from-yellow-400/50 hover:to-yellow-400/90 hover:scale-105 cursor-pointer animate-grow-bar"
                  style="height: 58%; animation-delay: 0.6s;"></div>
              </div>
              <div class="bar-wrapper w-full flex flex-col items-center">
                <div
                  class="bar w-full bg-gradient-to-t from-white/30 to-white/80 rounded-t-xl transition-all duration-300 hover:from-yellow-400/50 hover:to-yellow-400/90 hover:scale-105 cursor-pointer animate-grow-bar"
                  style="height: 85%; animation-delay: 0.7s;"></div>
              </div>
              <div class="bar-wrapper w-full flex flex-col items-center">
                <div
                  class="bar w-full bg-gradient-to-t from-white/30 to-white/80 rounded-t-xl transition-all duration-300 hover:from-yellow-400/50 hover:to-yellow-400/90 hover:scale-105 cursor-pointer animate-grow-bar"
                  style="height: 78%; animation-delay: 0.8s;"></div>
              </div>

              <!-- Trend line -->
              <div
                class="absolute top-[15%] left-[5%] right-[5%] h-0.5 bg-gradient-to-r from-transparent via-yellow-400 to-transparent transform -rotate-[5deg]"></div>
            </div>
          </div>

          <!-- Floating Badges -->
          <div
            class="absolute top-[10%] right-[10%] w-20 h-20 bg-gradient-to-br from-pink-400 to-red-500 rounded-full flex items-center justify-center text-white font-bold text-2xl shadow-lg shadow-pink-400/40 animate-pulse">
            🏆
          </div>
          <div
            class="absolute bottom-[20%] left-[5%] w-20 h-20 bg-gradient-to-br from-pink-400 to-red-500 rounded-full flex items-center justify-center text-white font-bold text-2xl shadow-lg shadow-pink-400/40 animate-pulse">
            ⚡
          </div>
        </div>
      </div>
    </div>

    <!-- Features Section -->
    <div class="relative z-10 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
      <div class="grid md:grid-cols-2 gap-8">
        <!-- Feature Card 1 - Discussie -->
        <div
          @click="goToDiscussion"
          class="bg-white p-10 rounded-3xl shadow-xl border-4 border-transparent transition-all duration-300 hover:-translate-y-3 hover:shadow-2xl hover:border-[#667eea] cursor-pointer group">
          <div
            class="w-[70px] h-[70px] bg-gradient-to-br from-[#667eea] to-[#764ba2] rounded-2xl flex items-center justify-center text-3xl mb-6 transition-transform group-hover:scale-110">
            💬
          </div>
          <h3 class="text-2xl font-bold text-gray-800 mb-4">Join de Discussie</h3>
          <p class="text-gray-600 leading-relaxed mb-6">
            Praat mee over politieke thema's die jou raken. Share jouw mening,
            debatteer met anderen en ontdek nieuwe perspectieven in een veilige omgeving.
          </p>
          <span class="text-[#667eea] font-semibold group-hover:underline">
            Bekijk →
          </span>
        </div>

        <!-- Feature Card 2 - Results -->
        <div
          @click="goToResults"
          class="bg-white p-10 rounded-3xl shadow-xl border-4 border-transparent transition-all duration-300 hover:-translate-y-3 hover:shadow-2xl hover:border-[#667eea] cursor-pointer group">
          <div
            class="w-[70px] h-[70px] bg-gradient-to-br from-[#667eea] to-[#764ba2] rounded-2xl flex items-center justify-center text-3xl mb-6 transition-transform group-hover:scale-110">
            📊
          </div>
          <h3 class="text-2xl font-bold text-gray-800 mb-4">Bekijk Stemresultaten</h3>
          <p class="text-gray-600 leading-relaxed mb-6">
            Volg verkiezingsuitslagen terwijl ze binnenkomen met onze live dashboard.
            Krijg instant updates en interactieve grafieken die laten zien wat er gebeurt.
          </p>
          <span class="text-[#667eea] font-semibold group-hover:underline">
            Bekijk →
          </span>
        </div>


      </div>
    </div>
  </div>

  <Footer/>
</template>

<style scoped>
/* Particle animation */
@keyframes float {
  0%, 100% {
    transform: translateY(0) translateX(0);
  }
  50% {
    transform: translateY(-20px) translateX(10px);
  }
}

/* Bar chart grow animation */
@keyframes grow-bar {
  from {
    transform: scaleY(0);
  }
  to {
    transform: scaleY(1);
  }
}

.animate-grow-bar {
  animation: grow-bar 1s ease-out forwards;
  transform-origin: bottom;
}

.bar {
  transform-origin: bottom;
}

/* Generate particles on mount */
</style>
