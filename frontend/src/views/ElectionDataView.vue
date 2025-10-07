<script setup lang="ts">
import { ElectionService } from '@/services/ElectionService';
import { ref, onMounted} from 'vue';

const electionData = ref<any>(null);

onMounted(async () => {
  const election: ElectionService  = new ElectionService();
  electionData.value = await election.getElectionData("TK2023", "constituency")
})


</script>

<template>
  <h1>Election Results</h1>

  <table v-if="electionData">
    <thead>
    <tr>
      <th>Party</th>
      <th>Constituency</th>
      <th>Votes</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(result, i) in electionData.partyConstituencyResults" :key="i">
      <td>{{ result.party.partyName }}</td>
      <td>{{ result.constituencyVotes.constituencyName }}</td>
      <td>{{ result.constituencyVotes.votes }}</td>
    </tr>
    </tbody>
  </table>
</template>

<style scoped>

</style>
