<script setup lang="ts">
import { ElectionService } from '@/services/ElectionService';
import { ref, onMounted} from 'vue';
import type { Election } from '@/interfaces/IElectionData';

const electionData = ref<Election>({partyConstituencyResults: []});
const loading = ref(true)

onMounted(async () => {
  const election: ElectionService  = new ElectionService();
  electionData.value = await election.getElectionData("TK2023", "constituency")
  if(electionData.value === null ) {
    loading.value = false
  }
})
</script>

<template>

  <!-- 📌 Container for page -->
  <div class="max-w-4xl mx-auto p-6 bg-white rounded-xl">
    <!-- Titel -->
    <h2 class="text-2xl font-bold text-gray-800 mb-6 flex items-center gap-2">
      📈 Partijen Ranking
    </h2>

    <!-- list of parties -->
    <div class="max-h-96 overflow-y-auto space-y-3">

      <div>
        v-if(load)
      </div>

      <div
        v-for="(result, i) in electionData.partyConstituencyResults"
        :key="i"
        class="bg-purple-50 rounded-2xl p-4 flex items-center justify-between hover:bg-purple-100 transition-colors shadow"
      >
        <!-- number + partyname -->
        <div class="flex items-center gap-4">
          <!-- circle with party ranking -->
          <div class="w-12 h-12 bg-purple-500 text-white rounded-full flex items-center justify-center font-bold text-lg flex-shrink-0">
            {{ i + 1 }}
          </div>

          <!-- Partyname -->
          <div class="text-lg font-bold text-gray-800">
            {{ result.party.partyName }}
          </div>
        </div>

        <!-- votes -->
        <div class="text-right">
          <div class="text-2xl font-bold text-purple-600">
            {{ result.constituencyVotes.votes}}
          </div>
          <div class="text-xs text-gray-400">stemmen</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
