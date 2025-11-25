<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { ElectionService } from '@/services/ElectionService';

const route = useRoute();
const electionService = new ElectionService();

const municipalityData = ref<any[]>([]);
const loading = ref(true);
const error = ref<string | null>(null);

const electionId = route.params.electionId as string;
const municipalityName = route.params.municipalityName as string;

onMounted(async () => {
  try {
    municipalityData.value = await electionService.getMunicipalityData(electionId, municipalityName);
  } catch (e) {
    error.value = 'Failed to load data';
    console.error(e);
  } finally {
    loading.value = false;
  }
});
</script>

<template>
  <div>
    <h1>Municipality: {{ municipalityName }}</h1>
    <div v-if="loading">Loading...</div>
    <div v-else-if="error">{{ error }}</div>

    <div v-else>
      <table>
        <thead>
        <tr>
          <th>Party</th>
          <th>Votes</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="vote in municipalityData" :key="vote.id">
          <td>{{ vote.party.registeredName }}</td>
          <td>{{ vote.votes }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
