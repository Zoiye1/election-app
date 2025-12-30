<script setup lang="ts">
import { computed, onMounted, ref, watch } from "vue";
import { ConstituencyService } from "@/services/ConstituencyService"
import type { TopConstituencies } from "@/interfaces/IElectionData"

const props = defineProps<{
  name: string;
  election: string;
  party: string | null;
}>();

const isEmpty = computed(() => props.party === null);
const error = ref<boolean>(false)
const errorMessage = `Er is geen data beschikbaar voor deze partij in dit jaar 😬 `
const emptyMessage = "Selecteer een partij";


const percentage = ref<number | null>(0);
const topConstituencies = ref<TopConstituencies[] | null>([])
const partyGrowth = ref<number | null>(0)

onMounted(async ()=>{

  try {
    percentage.value = await ConstituencyService.getConstituencyVotesPercentage(props.election, props.name, props.party)
  }
  catch (e){
    percentage.value = 0;

  }
  try {
    topConstituencies.value = await ConstituencyService.getTopPerformingConstituencyByParty(props.election, props.party)
  }
  catch (e){
    topConstituencies.value = []

  }

  try {

    const electionOrder = ["TK2021", "TK2023", "TK2025"]
    let currentIndex = electionOrder.indexOf(props.election)


    if (currentIndex <= 0 ) {
      return
    }

    const previousElection = electionOrder[currentIndex - 1];



    partyGrowth.value = await ConstituencyService.getPartyGrowth(previousElection, props.election, props.name, props.party)

  }
  catch (e){
    partyGrowth.value = 0
    error.value = true;
  }
})

watch(props, async (newValue) =>{
  try {
    percentage.value = await ConstituencyService.getConstituencyVotesPercentage(newValue.election, newValue.name, newValue.party)
    error.value = false;
  }
  catch (e){
    percentage.value = 0;
    error.value = true;

  }
  try {
    topConstituencies.value = await ConstituencyService.getTopPerformingConstituencyByParty(newValue.election, newValue.party)
  }
  catch (e){
    topConstituencies.value = []

  }

  try {

    const electionOrder = ["TK2021", "TK2023", "TK2025"]
    let currentIndex = electionOrder.indexOf(newValue.election)


    if (currentIndex <= 0 ) {
      return
    }

    const previousElection = electionOrder[currentIndex - 1];



    partyGrowth.value = await ConstituencyService.getPartyGrowth(previousElection, newValue.election, newValue.name, newValue.party)

  }
  catch (e){
    partyGrowth.value = 0
    error.value = true;
  }
})
const formattedPercentage = computed(() => {
  if (percentage.value == null ) {
    return  -9
  }
  if (percentage.value < 0.1) {
    return "minder dan 0,1%";
  }
  return `${percentage.value}%`;
})

const formattedGrowth = computed(() => {

  if(!partyGrowth.value){
    return
  }
  if (partyGrowth.value === 0) {
    return "geen verschil ten opzichte van de vorige verkiezing";
  }

  const absValue = Math.abs(partyGrowth.value).toLocaleString("nl-NL");

  if (partyGrowth.value > 0) {
    return `${absValue} meer stemmen dan de vorige verkiezing`;
  }

  return `${absValue} minder stemmen dan de vorige verkiezing`;
});

</script>

<template>
  <div class="max-w-4xl mx-auto p-6 bg-white rounded-xl shadow-md">


    <!-- Title -->
    <div class="flex items-center justify-between mb-6">
      <h2 class="text-2xl font-bold text-gray-800 flex items-center gap-2">
        🔎 Partij Informatie
        <span v-if="!isEmpty" class="text-purple-600">
          {{ props.party }}
        </span>
      </h2>
    </div>

    <!-- If there's no selected party -->
    <div v-if="isEmpty" class="text-xl font-bold text-purple-600">
      {{ emptyMessage }}
    </div>

    <div v-else-if="error" class="bg-red-50 text-red-600 border border-red-200 rounded-lg px-4 py-3 mb-4">
      {{ errorMessage }}
    </div>
    <div v-else>
      <div class="bg-white shadow-md p-6 rounded-xl border border-purple-100 mb-5">
        <p class="text-gray-800 text-sm mb-2">Aandeel binnen de kieskring</p>

        <p class="text-gray-700 text-lg font-bold">
          Deze partij behaalde
          <span class="text-purple-600 font-bold">{{formattedPercentage}}</span>
          van alle uitgebrachte stemmen in deze kieskring.
        </p>
      </div>


      <div class="bg-white shadow-md p-6 rounded-xl border border-purple-100 mb-5">
        <p class="text-gray-800 text-sm mb-2">Top 5 populairste kieskringen</p>
        <ul>
          <li v-for="(c, i) in topConstituencies" :key="c.constituencyName">
            <span class= "text-gray-700 text-lg font-bold"> {{i + 1}}. {{ c.constituencyName }}</span> | <span class="text-purple-600 font-bold">{{ c.votes.toLocaleString() }} stemmen</span>
          </li>
        </ul>
      </div>

      <div class="bg-white shadow-md p-6 rounded-xl border border-purple-100 mb-5">
        <p class="text-gray-800 text-sm mb-2">Verschil ten opzichte van vorig jaar</p>

        <p class="text-gray-700 text-lg font-bold">
          Deze partij heeft
          <span v-if="partyGrowth"

                :class="partyGrowth > 0 ? 'text-green-600' : partyGrowth < 0 ? 'text-red-600' : 'text-gray-600'"
                class="font-bold"
          >
          {{ formattedGrowth }}
        </span>
          <span v-if="!partyGrowth" class="font-bold text-orange-300">
         geen data in de vorige verkiezing
        </span>
        </p>
      </div>
    </div>
  </div>
</template>
