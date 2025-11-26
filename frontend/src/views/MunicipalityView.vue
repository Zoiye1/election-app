<template>
  <div>
    <h1>All Municipality Results</h1>

    <div v-if="loading">Loading...</div>
    <div v-else-if="error">{{ error }}</div>

    <div v-else>
      <div v-for="(votes, municipalityName) in groupedData" :key="municipalityName" style="margin-bottom: 2rem;">
        <h2>{{ municipalityName }}</h2>
        <table>
          <thead>
          <tr>
            <th>Party</th>
            <th>Votes</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="vote in votes" :key="vote.id">
            <td>{{ vote.party.registeredName }}</td>
            <td>{{ vote.votes }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { ElectionService } from '@/services/ElectionService';

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
