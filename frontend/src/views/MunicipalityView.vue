
<template>
  <main>
    <Navbar/>
  </main>
  <div class="container">
    <div v-if="loading" class="loading">Loading data...</div>
    <div v-else-if="error" class="error">{{ error }}</div>

    <div v-else class="results">
      <div v-for="(votes, municipalityName) in groupedData" :key="municipalityName" class="municipality-card">
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
            <td>{{ vote.votes.toLocaleString() }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import Navbar from '../components/Navbar.vue'
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

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  color: #2c3e50;
  margin-bottom: 30px;
}

.loading, .error {
  text-align: center;
  padding: 40px;
  font-size: 18px;
}

.error {
  color: #e74c3c;
}

.results {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(400px, 1fr));
  gap: 20px;
}

.municipality-card {
  background: white;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.municipality-card h2 {
  margin-top: 0;
  color: #34495e;
  border-bottom: 2px solid #3498db;
  padding-bottom: 10px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 15px;
}

th {
  background: #f8f9fa;
  padding: 10px;
  text-align: left;
  font-weight: 600;
}

td {
  padding: 8px 10px;
  border-bottom: 1px solid #eee;
}

tr:hover {
  background: #f8f9fa;
}
</style>
