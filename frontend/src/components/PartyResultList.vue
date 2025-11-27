
<script setup lang="ts">


// Props
import type { TopNationalParty } from '@/interfaces/IElectionData.ts'
import { ref } from 'vue'

const props = defineProps<{
  electionId: string
}>()


//TODO: create composables for shared states
// State
const partyResults = ref<TopNationalParty[]>([])
const loading = ref<boolean>(true)
const error = ref<string | null>(null)
</script>


<template>
</template>
