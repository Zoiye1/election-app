<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import { ElectionService } from '@/services/ElectionService';
import { ref, onMounted} from 'vue';
import type { Election } from '@/interfaces/IElectionData';
import Navbar from "../components/Navbar.vue";
import Footer from '@/components/Footer.vue';

const electionData = ref<Election>({partyConstituencyResults: []});

onMounted(async () => {
  const election: ElectionService  = new ElectionService();
  electionData.value = await election.getElectionData("TK2023", "constituency")
})


</script>

<template>
 <main>
  <Navbar/>
</main>

  <!-- 📌 Container voor de hele pagina -->
  <div class="max-w-4xl mx-auto p-6">
    <!-- Titel -->
    <h1 class="text-2xl font-bold text-gray-800 center text-center">Verkiezingsuitslagen</h1>
    <p class="text-gray-600 mb-6 text-center">Kieskring Amsterdam</p>

    <!-- Tabel -->
    <div class="max-h-96 overflow-y-auto border-4 border-[#F97316] rounded-lg shadow">
      <table class="w-full border-collapse stroke-current stroke-3 text-blue-500">
        <!-- Header -->
        <thead class="bg-blue-500 text-white text-left sticky top-0 z-10">
        <tr>
          <th class="py-2 px-4">Partij</th>
          <th class="py-2 px-4 text-right">Totale Stemmen</th>
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
  <Footer/>
</template>

<style scoped>

</style>
