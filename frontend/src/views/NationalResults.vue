<script setup lang="ts">
import { ElectionService } from '@/services/ElectionService';
import { ref, onMounted } from 'vue';
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';
import TotalNationalVotesComponent from '@/components/TotalNationalVotesComponent.vue';

const totalCounted = ref<number>(0);

onMounted(async () => {

  totalCounted.value = await ElectionService.getTotalVotes();
});
</script>

<template>
  <main>
    <Navbar />
  </main>

  <div class="max-w-4xl mx-auto p-6">
    <h1 class="text-3xl font-bold text-gray-800 text-center mb-8">
      Nationale Verkiezingsuitslagen 2023
    </h1>

    <TotalNationalVotesComponent :totalCounted="totalCounted" />
  </div>

  <Footer />
</template>

<style scoped>
</style>
