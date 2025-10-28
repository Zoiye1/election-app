<script setup lang="ts">
import { ElectionService } from '@/services/ElectionService';
import { ref, onMounted} from 'vue';
import type { Election } from '@/interfaces/IElectionData';

const electionData = ref<Election>({partyConstituencyResults: []});

onMounted(async () => {
  const election: ElectionService  = new ElectionService();
  electionData.value = await election.getElectionData("TK2023", "constituency")
})
</script>

<template>

  <!-- 📌 Container for page -->
  <div class="max-w-4xl mx-auto p-6">
    <!-- Tabel -->
    <div class="max-h-96 overflow-y-auto rounded-lg shadow">
      <table class="relative w-full bg-white/10 backdrop-blur-lg rounded-3xl p-8 shadow-2xl">
        <!-- Header -->
        <thead class="bg-white text-black text-left sticky top-0 z-10">
        <tr>
          <th class="text-2xl font-bold text-gray-800 mb-4">Partijen Ranking</th>
        </tr>
        </thead>

        <!-- Body -->
        <tbody>
        <tr v-for="(result, i) in electionData.partyConstituencyResults" :key="i"
            class="border-t hover:bg-orange-50">
          <!-- Partij -->
          <td class="py-2 px-4 font-medium text-gray-800">
            {{ result.party.partyName }}
          </td>
          <!-- Stemmen -->
          <td class="py-2 px-4 text-right font-bold text-gray-800">
            {{ result.constituencyVotes.votes }}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped>

</style>
