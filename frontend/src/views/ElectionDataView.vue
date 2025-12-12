<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import { onMounted, ref } from 'vue';
import Parties_ConstituencyComponent from '@/components/ConstituencyComponents/Parties_ConstituencyComponent.vue';
import ConstituencyVotesPercentage from '@/components/ConstituencyComponents/ConstituencyVotesPercentage.vue';
import ConstituencyFilter from '@/components/Filters/ConstituencyFilter.vue';
import Navbar from '@/components/Navbar.vue';
import ElectionSelector from '@/components/ElectionSelector.vue';
import { useElection } from '@/composables/useElection';
import PageNavigator from '@/components/PageNavigator.vue';
import TotalConstituencyVotes from '@/components/ConstituencyComponents/TotalConstituencyVotes.vue';
import PartyInformationComponent from '@/components/ConstituencyComponents/PartyInformationComponent.vue';

const selectedParty = ref<string | null>(null)
const selectedConstituency = ref<string>("Amsterdam");
const { selectedElection } = useElection();

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
    <Navbar />
  </main>

  <div class="min-h-screen bg-gradient-to-br from-[#667eea] to-[#764ba2] relative overflow-hidden">

    <div class="mt-15 max-w-8xl mx-auto px-6 py-10 flex flex-col gap-10">

      <div class="text-center text-white">
        <PageNavigator />
      </div>


      <div class="mb-1">
        <ElectionSelector />
      </div>



      <!-- FILTER + PERCENTAGE -->
      <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">

        <div>
          <TotalConstituencyVotes :name="selectedConstituency" :election="selectedElection" />
        </div>

        <!-- FILTER -->
        <div class="bg-white/20 backdrop-blur-md p-6 rounded-2xl shadow-lg">
          <h2 class="text-white font-semibold text-lg mb-4">Selecteer een kieskring</h2>
          <ConstituencyFilter v-model="selectedConstituency" class="w-full" />
        </div>

        <!-- PERCENTAGE -->
        <div>
          <ConstituencyVotesPercentage :name="selectedConstituency" :election="selectedElection" />
        </div>
      </div>
      
      <!-- RESULTS -->
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-5">

        <div>
          <Parties_ConstituencyComponent :name="selectedConstituency" :election="selectedElection"  @partySelected="selectedParty = $event"/>
        </div>

        <div>
          <PartyInformationComponent :name ="selectedConstituency" :election="selectedElection" :party = "selectedParty"/>
        </div>
      </div>
    </div>

    <div class="fixed inset-0 pointer-events-none" id="particles"></div>
  </div>
</template>

