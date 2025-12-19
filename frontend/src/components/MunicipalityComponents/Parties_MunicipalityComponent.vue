<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import { ElectionService } from '@/services/ElectionService';
import ElectionSelector from "@/components/ElectionSelector.vue";
import { useElection } from '@/composables/useElection'

const { selectedElection } = useElection()
const electionData = ref<any[]>([]);
const loading = ref(true);
const error = ref<string>("");

const props = defineProps<{
  name: string
  election: string
}>();

onMounted(async () => {
  const electionService = new ElectionService();
  try {
    const data = await electionService.getMunicipalityData(props.election, props.name);
    if (!data || data.length === 0) {
      error.value = "Oeps, er is iets mis gegaan. Probeer het later weer opnieuw";
    } else {
      electionData.value = data;
    }
  } catch (err) {
    error.value = "Oeps, er is iets mis gegaan. Probeer het later weer opnieuw";
    electionData.value = [];
  }
  loading.value = false;
});

watch(props, async (newValue) => {
  loading.value = true;
  const electionService = new ElectionService();
  try {
    const data = await electionService.getMunicipalityData(props.election, newValue.name);
    electionData.value = data;
  } catch (err) {
    error.value = "Oeps, er is iets mis gegaan. Probeer het later weer opnieuw";
    electionData.value = [];
  }
  loading.value = false;
});
</script>

<template>

  <div class="mb-1">
    <ElectionSelector />
  </div>

  <div class="max-w-4xl mx-auto p-6 bg-white rounded-xl shadow-md">
    <div class="flex items-center justify-between mb-6">
      <h2 class="text-2xl font-bold text-gray-800 flex items-center gap-2">
        📈 Partijen Ranking
      </h2>
    </div>

    <div v-if="error" class="bg-red-50 text-red-600 border border-red-200 rounded-lg px-4 py-3 mb-4">
      {{ error }}
    </div>
    <div v-if="loading" class="text-center text-gray-500 py-6">Laden...</div>

    <div v-else class="max-h-96 overflow-y-auto space-y-3 pr-2">
      <div
        v-for="(result, i) in electionData"
        :key="i"
        class="bg-purple-50 rounded-2xl p-4 flex items-center justify-between hover:bg-purple-100 transition-colors shadow"
      >
        <div class="flex items-center gap-4">
          <div
            class="w-12 h-12 bg-[linear-gradient(135deg,#667eea_0%,#764ba2_100%)] text-white rounded-full flex items-center justify-center font-bold text-lg flex-shrink-0"
          >
            {{ i + 1 }}
          </div>
          <div class="text-lg font-bold text-gray-800">
            {{ result.partyName }}
          </div>
        </div>

        <div class="text-right">
          <div class="text-2xl font-bold text-purple-600">
            {{ result.votes.toLocaleString("nl-NL") }}
          </div>
          <div class="text-xs text-gray-400">stemmen</div>
        </div>
      </div>
    </div>
  </div>
</template>
