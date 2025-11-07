<script setup lang="ts">
import { ElectionService } from '@/services/ElectionService';
import { ref, onMounted } from 'vue';
import type { ConstituencyPartyVotes } from '@/interfaces/IElectionData';

const electionData = ref<ConstituencyPartyVotes[]>([]);
const loading = ref(true);

onMounted(async () => {
  const electionService = new ElectionService();
  const election = await electionService.getElectionData("TK2023", "Amsterdam");

  electionData.value = election;

  if (election ) {
  }

  loading.value = false;
});
</script>

<template>
  <div class="max-w-4xl mx-auto p-6 bg-white rounded-xl">
    <h2 class="text-2xl font-bold text-gray-800 mb-6 flex items-center gap-2">
      📈 Partijen Ranking
    </h2>

    <div class="max-h-96 overflow-y-auto space-y-3">
      <div
        v-for="(result, i) in electionData"
        :key="result.id"
        class="bg-purple-50 rounded-2xl p-4 flex items-center justify-between hover:bg-purple-100 transition-colors shadow"
      >
        <div class="flex items-center gap-4">
          <div class="w-12 h-12 bg-purple-500 text-white rounded-full flex items-center justify-center font-bold text-lg flex-shrink-0">
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
