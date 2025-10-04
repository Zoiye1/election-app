<template>


<footer class="bg-blue-500 text-white mt-auto border-t-4 border-orange-500">
    <div class="w-full max-w-screen-xl mx-auto px-6 py-6 md:py-8">
        <!-- Top section met logo en links -->
        <div class="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-6">
            <!-- Logo -->
            <div class="flex items-center">
                <div class="bg-white rounded-xl p-1.5 shadow-md">
                    <img src="../assets/logo.png" alt="StemSlim Logo" class="h-8 sm:h-10 rounded-lg" />
                </div>
            </div>

            <!-- Navigation Links -->
            <nav>
                <ul class="flex flex-wrap items-center gap-4 sm:gap-6 text-sm font-medium">
                    <li>
                        <a href="#" class="hover:text-orange-300 transition-colors duration-200">About</a>
                    </li>
                    <li>
                        <a href="#" class="hover:text-orange-300 transition-colors duration-200">Privacy Policy</a>
                    </li>
                    <li>
                        <a href="#" class="hover:text-orange-300 transition-colors duration-200">Licensing</a>
                    </li>
                    <li>
                        <a href="#" class="hover:text-orange-300 transition-colors duration-200">Contact</a>
                    </li>
                </ul>
            </nav>
        </div>

        <!-- Orange line -->
        <hr class="border-orange-400 opacity-50 my-4" />

        <!-- Copyright - compacter -->
        <div class="text-center sm:text-left">
            <p class="text-xs text-blue-100">
                © 2023 <a href="#" class="hover:text-orange-300 transition-colors duration-200 font-semibold">Flowbite™</a>. All Rights Reserved.
            </p>
        </div>
    </div>
</footer>


</template>
