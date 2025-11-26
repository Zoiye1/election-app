<script setup lang="ts">
import { ref, watch } from 'vue';
import { ElectionService } from '@/services/ElectionService';
import Navbar from '@/components/Navbar.vue';
import PageNavigator from '@/components/PageNavigator.vue';

const electionService = new ElectionService();

const municipalityData = ref<any[]>([]);
const loading = ref(false);
const error = ref<string | null>(null);
const selectedMunicipality = ref<string>('Amsterdam');

const municipalities = ref<string[]>([
  'Amsterdam', 'Rotterdam', 'Den Haag', 'Utrecht', 'Eindhoven',
  'Groningen', 'Tilburg', 'Almere', 'Breda', 'Nijmegen',
  'Enschede', 'Haarlem', 'Arnhem', 'Zaanstad', 'Amersfoort'
  // voeg meer toe
]);

const loadMunicipalityData = async () => {
  if (!selectedMunicipality.value) return;

  loading.value = true;
  error.value = null;

  try {
    municipalityData.value = await electionService.getMunicipalityData('TK2023', selectedMunicipality.value);
  } catch (e) {
    error.value = 'Failed to load data';
    console.error(e);
  } finally {
    loading.value = false;
  }
};

watch(selectedMunicipality, loadMunicipalityData, { immediate: true });
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-[#667eea] to-[#764ba2]">
    <Navbar />

    <div class="max-w-5xl mx-auto px-6 py-10">
      <PageNavigator />

      <h1 class="text-white text-3xl font-bold text-center my-8">Municipality Results</h1>

      <div class="bg-white/10 backdrop-blur-sm p-6 rounded-lg mb-8">
        <label class="text-white font-semibold mb-2 block">Select Municipality:</label>
        <select
          v-model="selectedMunicipality"
          class="w-full px-4 py-2 rounded-lg bg-white text-gray-800"
        >
          <option v-for="municipality in municipalities" :key="municipality" :value="municipality">
            {{ municipality }}
          </option>
        </select>
      </div>

      <div v-if="loading" class="text-center text-white text-xl">Loading...</div>
      <div v-else-if="error" class="text-center text-red-300 text-xl">{{ error }}</div>

      <div v-else-if="municipalityData.length > 0" class="bg-white/10 backdrop-blur-sm p-6 rounded-lg">
        <h2 class="text-white text-xl font-semibold mb-4">{{ selectedMunicipality }}</h2>
        <table class="w-full">
          <thead>
          <tr class="border-b border-white/20">
            <th class="text-left text-white py-2">Party</th>
            <th class="text-right text-white py-2">Votes</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="vote in municipalityData" :key="vote.id" class="border-b border-white/10">
            <td class="text-white py-2">{{ vote.party.registeredName }}</td>
            <td class="text-white text-right py-2">{{ vote.votes.toLocaleString() }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
