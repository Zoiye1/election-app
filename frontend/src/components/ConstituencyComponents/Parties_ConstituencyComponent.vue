<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import type { ConstituencyPartyVotes } from '@/interfaces/IElectionData';
import { ConstituencyService } from '@/services/ConstituencyService';

// electionData holds the array of constituency party votes
const electionData = ref<ConstituencyPartyVotes[]>([]);
const loading = ref(true);
const props = defineProps<{
  name: string
  election: string
}>();
const error = ref<string>("")


onMounted(async () => {

  // we try to get the election data and fill electioData with the array of PartyConstituencyVotes of the
  // selected constituency
  const constituencyService = new ConstituencyService();
  try{
    const election = await ConstituencyService.getConstituencyData(props.election, props.name);
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
watch(props, async (newValue) => {
  loading.value = true;
  const constituencyService = new ConstituencyService();
  try {
    const election = await ConstituencyService.getConstituencyData(props.election, newValue.name);
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
            {{ result.partyName }}
          </div>
        </div>

        <div class="text-right">
          <div class="text-2xl font-bold text-purple-600">
            {{ result.votes.toLocaleString("nl-NL") }}
          </div>
          <div class="text-xs text-gray-400">stemmen</div>
        </div>
      </div>
    </div>
  </div>
</template>
