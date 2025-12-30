<script setup lang="ts">
import type { CandidateResult } from '@/interfaces/CandidateResult'

const props = defineProps<{
  candidate: CandidateResult
  ranking: number
}>()

const emit = defineEmits<{
  close: []
}>()
</script>

<template>
<!--  dimmed background-->
  <div class="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
<!--    white card-->
    <div class="bg-white rounded-3xl p-6 w-full max-w-md mx-4 shadow-2xl">

      <div class="flex items-center justify-between mb-6">
        <!-- Ranking badge -->
        <div class="bg-purple-600 text-white px-4 py-2 rounded-full text-sm font-semibold flex items-center gap-2">
          ⚡ #{{ props.ranking }} Nationale Ranking
        </div>

        <!-- return button -->
        <button
          @click="emit('close')"
          class="text-gray-500 hover:text-gray-700 flex items-center gap-1"
        >
          ← Terug
        </button>
      </div>

      <h1 class="text-3xl font-bold text-gray-800 mb-4">
        {{ props.candidate.fullName }}
      </h1>

      <div class="mb-6">
        <p class="text-sm text-gray-500 mb-2">Partij</p>
        <div class="inline-flex items-center gap-2 px-4 py-2 rounded-xl border-2 border-gray-200">
          <span class="w-4 h-4 rounded bg-orange-500"></span>
          <span class="font-semibold text-gray-700">{{ props.candidate.party }}</span>
        </div>
      </div>

      <div class="bg-gradient-to-br from-purple-500 to-indigo-600 rounded-2xl p-6 mb-6 text-white">
        <div class="flex items-center justify-center gap-2 mb-2">
          <span class="text-white/80 font-medium">👥 Totaal Stemmen</span>
        </div>
        <p class="text-4xl font-bold text-center">
          {{ props.candidate.votes.toLocaleString('nl-NL') }}
        </p>
      </div>

      <div class="grid grid-cols-2 gap-4">
        <!-- Nationaal Percentage -->
        <div class="bg-gray-50 rounded-2xl p-4">
          <p class="text-sm text-gray-500 font-medium mb-1">Nationaal Percentage</p>
          <p class="text-2xl font-bold text-purple-500">0%</p>
          <p class="text-xs text-gray-400 mb-3">van alle stemmen</p>
          <div class="w-full bg-gray-200 rounded-full h-2">
            <div class="bg-purple-500 h-full rounded-full" style="width: 0%"></div>
          </div>
        </div>

        <div class="bg-gray-50 rounded-2xl p-4">
          <p class="text-sm text-gray-500 font-medium mb-1">Partij Percentage</p>
          <p class="text-2xl font-bold text-purple-500">0%</p>
          <p class="text-xs text-gray-400 mb-3">van alle {{ props.candidate.party }} stemmen</p>
          <div class="w-full bg-gray-200 rounded-full h-2">
            <div class="bg-purple-500 h-full rounded-full" style="width: 0%"></div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>
