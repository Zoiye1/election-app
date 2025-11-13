<script setup lang="ts">
import { ElectionService } from '@/services/ElectionService';
import { ref, onMounted, watch } from 'vue';
import type { ConstituencyPartyVotes } from '@/interfaces/IElectionData';

// electionData holds the array of constituency party votes
const electionData = ref<ConstituencyPartyVotes[]>([]);
const loading = ref(true);
const selectedConstituency = ref<string>('Amsterdam');
const error = ref<string>("")

const constituencies = [
  'Groningen', 'Leeuwarden', 'Assen', 'Zwolle', 'Lelystad', 'Nijmegen', 'Arnhem',
  'Utrecht', 'Amsterdam', 'Haarlem', 'Den Haag', 'Rotterdam', 'Dordrecht',
  'Middelburg', 'Tilburg', '’s-Hertogenbosch', 'Maastricht', 'Eindhoven',
  'Leiden', 'Enschede'
];

const open = ref(false);

const toggleDropdown = () => {
  open.value = !open.value;
};

const selectConstituency = (constituency: string) => {
  selectedConstituency.value = constituency;
  open.value = false;
};

onMounted(async () => {

  // we try to get the election data and fill electioData with the array of PartyConstituencyVotes of the
  // selected constituency
  const electionService = new ElectionService();
  try{
    const election = await electionService.getConstituencyData("TK2023", selectedConstituency.value);
    if(!election || election.length == 0){
      error.value = "Oeps, er is iets mis gegaan. Probeer het later weer opnieuw";

    }
    else {
      electionData.value = election;
    }
  }
  // if the fetch wasn't successfull we display an error
  catch (err){
    error.value = "Oeps, er is iets mis gegaan. Probeer het later weer opnieuw";
    electionData.value = [];
  }
  loading.value = false;
});

// we update the selected constituency if the user selects something else
watch(selectedConstituency, async (newValue) => {
  loading.value = true;
  const electionService = new ElectionService();
  try {
    const election = await electionService.getElectionData("TK2023", newValue);
    electionData.value = election;
  }
  catch (err){
    error.value = "Oeps, er is iets mis gegaan. Probeer het later weer opnieuw";
    electionData.value = [];
    loading.value = false;
  }
  loading.value = false;
});
</script>

<template>
  <div class="max-w-4xl mx-auto p-6 bg-white rounded-xl shadow-md">
    <div class="flex items-center justify-between mb-6">
      <h2 class="text-2xl font-bold text-gray-800 flex items-center gap-2">
        📈 Partijen Ranking
      </h2>

      <!-- Dropdown -->
      <div class="relative w-56 text-gray-700">
        <button
          @click="toggleDropdown"
          class="w-full flex items-center justify-between bg-white border border-gray-300 rounded-xl px-4 py-2 shadow-sm
                 hover:border-purple-400 focus:ring-2 focus:ring-purple-200 focus:border-purple-500 transition-all duration-200"
        >
          <span>{{ selectedConstituency || 'Kieskring' }}</span>
          <svg
            class="w-4 h-4 ml-2 text-gray-500 transition-transform duration-200"
            :class="{ 'rotate-180': open }"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
          >
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
          </svg>
        </button>

        <transition
          enter-active-class="transition ease-out duration-200"
          enter-from-class="opacity-0 translate-y-1"
          enter-to-class="opacity-100 translate-y-0"
          leave-active-class="transition ease-in duration-150"
          leave-from-class="opacity-100 translate-y-0"
          leave-to-class="opacity-0 translate-y-1"
        >
          <ul
            v-if="open"
            class="absolute bottom-full mb-2 w-full bg-white border border-gray-200 rounded-xl shadow-lg max-h-20 overflow-y-auto z-10"
          >
            <li
              v-for="(city, index) in constituencies"
              :key="index"
              @click="selectConstituency(city)"
              class="px-4 py-2 cursor-pointer hover:bg-purple-50 transition-colors rounded-lg"
            >
              {{ city }}
            </li>
          </ul>
        </transition>
      </div>
    </div>

    <!--  Result list -->
    <div v-if="error" class="bg-red-50 text-red-600 border border-red-200 rounded-lg px-4 py-3 mb-4">
      {{ error }}
    </div>
    <div v-if="loading" class="text-center text-gray-500 py-6">Laden...</div>

    <div v-else class="max-h-96 overflow-y-auto space-y-3 pr-2">
      <div
        v-for="(result, i) in electionData"
        :key="result.id"
        class="bg-purple-50 rounded-2xl p-4 flex items-center justify-between hover:bg-purple-100 transition-colors shadow"
      >
        <div class="flex items-center gap-4">
          <div
            class="w-12 h-12 bg-purple-500 text-white rounded-full flex items-center justify-center font-bold text-lg flex-shrink-0"
          >
            {{ i + 1 }}
          </div>
          <div class="text-lg font-bold text-gray-800">
            {{ result.party.registeredName }}
          </div>
        </div>

        <div class="text-right">
          <div class="text-2xl font-bold text-purple-600">
            {{ result.votes }}
          </div>
          <div class="text-xs text-gray-400">stemmen</div>
        </div>
      </div>
    </div>
  </div>
</template>
