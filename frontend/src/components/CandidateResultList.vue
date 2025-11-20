<script setup lang="ts">
import { ref, watch } from 'vue';
import { getTopCandidatesByElection } from '@/services/candidateResultService';
import type { CandidateResult } from '@/interfaces/candidateResult';

//props
const props = defineProps<{
  electionId: string;
}>();

// State
const candidateResults = ref<CandidateResult[]>([]);
const loading = ref<boolean>(true);
const error = ref<string | null>(null);
