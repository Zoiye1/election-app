<script setup lang="ts">
import { onMounted, ref, computed } from 'vue';
import { ElectionService } from '@/services/ElectionService';
import Navbar from '@/components/Navbar.vue';
import PageNavigator from '@/components/PageNavigator.vue';

const electionService = new ElectionService();

const municipalityData = ref<any[]>([]);
const loading = ref(true);
const error = ref<string | null>(null);

const groupedData = computed(() => {
  const grouped: Record<string, any[]> = {};
  municipalityData.value.forEach(vote => {
    const name = vote.municipality.name;
    if (!grouped[name]) {
      grouped[name] = [];
    }
    grouped[name].push(vote);
  });
  return grouped;
});

onMounted(async () => {
  try {
    municipalityData.value = await electionService.getAllMunicipalityData('TK2023');
  } catch (e) {
    error.value = 'Failed to load data';
    console.error(e);
  } finally {
    loading.value = false;
  }
});
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-[#667eea] to-[#764ba2]">
    <Navbar />

    <div class="max-w-7xl mx-auto px-6 py-10">
      <PageNavigator />

      <h1 class="text-white text-3xl font-bold text-center my-8">Municipality Results</h1>

      <div v-if="loading" class="text-center text-white text-xl">Loading...</div>
      <div v-else-if="error" class="text-center text-red-300 text-xl">{{ error }}</div>

      <div v-else class="space-y-8">
        <div v-for="(votes, municipalityName) in groupedData" :key="municipalityName"
             class="bg-white/10 backdrop-blur-sm p-6 rounded-lg">
          <h2 class="text-white text-xl font-semibold mb-4">{{ municipalityName }}</h2>
          <table class="w-full">
            <thead>
            <tr class="border-b border-white/20">
              <th class="text-left text-white py-2">Party</th>
              <th class="text-right text-white py-2">Votes</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="vote in votes" :key="vote.id" class="border-b border-white/10">
              <td class="text-white py-2">{{ vote.party.registeredName }}</td>
              <td class="text-white text-right py-2">{{ vote.votes.toLocaleString() }}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
