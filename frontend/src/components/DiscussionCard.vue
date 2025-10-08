<script setup lang="ts">
interface Discussion {
  id: number
  title: string
  content: string
  author: {
    id: number
    username: string
  }
  createdAt: string
  views: number
  replies: number
}

const props = defineProps<{
  discussion: Discussion
}>()

const formatDate = (dateString: string) => {
  const date = new Date(dateString)
  return date.toLocaleDateString('nl-NL', {
    day: '2-digit',
    month: 'long',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  })
}
</script>

<template>
  <div class="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow">
    <div class="flex gap-4">
      <!-- User Avatar -->
      <div class="flex-shrink-0">
        <div class="w-12 h-12 bg-cyan-400 rounded-full flex items-center justify-center">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7 text-white" viewBox="0 0 20 20" fill="currentColor">
            <path fill-rule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clip-rule="evenodd" />
          </svg>
        </div>
      </div>

      <!-- Discussion Content -->
      <div class="flex-1 min-w-0">
        <!-- Title -->
        <h2 class="text-xl font-semibold text-gray-900 mb-2">
          {{ discussion.title }}
        </h2>

        <!-- Meta Info -->
        <p class="text-sm text-gray-500 mb-3">
          Gestart door <span class="font-medium">{{ discussion.author.username }}</span> op {{ formatDate(discussion.createdAt) }}
        </p>

        <!-- Content Preview -->
        <p class="text-gray-700 mb-4">
          {{ discussion.content }}
        </p>

        <!-- Stats and Button -->
        <div class="flex justify-between items-center">
          <div class="text-sm text-gray-500">
            {{ discussion.views }} weergaven · {{ discussion.replies }} reacties
          </div>
          <button
            class="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-6 py-2 rounded-lg transition-colors">
            Meer lezen
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
