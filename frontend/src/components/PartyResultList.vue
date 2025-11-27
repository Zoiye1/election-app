<script setup lang="ts">


// Props
import type { TopNationalParty } from '@/interfaces/IElectionData.ts'
import { ref } from 'vue'
import { NationalPartyService } from '@/services/NationalPartyService.ts'

const props = defineProps<{
  electionId: string
}>()


//TODO: create composables for shared states
// State
const partyResults = ref<TopNationalParty[]>([])
const loading = ref<boolean>(true)
const error = ref<string | null>(null)

async function fetchPartyResults() {
  loading.value = true
  error.value = null

  try {
    // Fetch data from API
    partyResults.value = await NationalPartyService.getTopParties(props.electionId)
    } catch (err) {
    // If it does not succeed display error
    error.value = 'Failed to fetch party results'
    console.error(err)
    } finally{
    loading.value = false
  }
}
</script>

<template></template>
