import React, { createContext, useState, useContext, useEffect } from 'react';

const RecipeContext = createContext();

const INITIAL_RECIPES = [
    {
        id: 1,
        title: "Spicy Basil Chicken",
        description: "A classic Thai street food dish with fresh basil, chilies, and tender chicken breast.",
        introduction: "This authentic Pad Krapow Gai recipe brings the heat of Thai street food right to your kitchen. Quick, easy, and incredibly flavorful.",
        image: "https://images.unsplash.com/photo-1564436872-f6d81182df12?auto=format&fit=crop&q=80&w=800",
        time: "20 min",
        servings: "2 ppl",
        likes: 124,
        isLiked: false,
        category: "Lunch",
        difficulty: "Medium",
        author: "Chef John",
        isPublic: true
    },
    {
        id: 2,
        title: "Avocado Toast with Egg",
        description: "Creamy avocado on toasted sourdough topped with a perfectly poached egg.",
        introduction: "The ultimate breakfast power-up. Good fats, protein, and crunch all in one perfect bite.",
        image: "https://images.unsplash.com/photo-1525351484163-7529414395d8?auto=format&fit=crop&q=80&w=800",
        time: "10 min",
        servings: "1 ppl",
        likes: 89,
        isLiked: true,
        category: "Breakfast",
        difficulty: "Easy",
        author: "HealthyEats",
        isPublic: true
    },
    {
        id: 3,
        title: "Berry Smoothie Bowl",
        description: "Refreshing mixed berry smoothie topped with granola, chia seeds, and fresh fruit.",
        introduction: "Start your day with a burst of antioxidants and color. This smoothie bowl is as beautiful as it is delicious.",
        image: "https://images.unsplash.com/photo-1626078436812-78d2b9e7b12d?auto=format&fit=crop&q=80&w=800",
        time: "5 min",
        servings: "1 ppl",
        likes: 256,
        isLiked: false,
        category: "Breakfast",
        difficulty: "Easy",
        author: "SmoothieQueen",
        isPublic: true
    },
    {
        id: 4,
        title: "Mushroom Risotto",
        description: "Rich and creamy Italian rice dish cooked with wild mushrooms and parmesan.",
        introduction: "A labor of love that rewards you with the creamiest, earthiest rice dish imaginable. Perfect for date night.",
        image: "https://images.unsplash.com/photo-1476124369491-e7addf5db371?auto=format&fit=crop&q=80&w=800",
        time: "45 min",
        servings: "4 ppl",
        likes: 67,
        isLiked: false,
        category: "Dinner",
        difficulty: "Hard",
        author: "ItalianMaster",
        isPublic: true
    }
];

export function RecipeProvider({ children }) {
    const [recipes, setRecipes] = useState(INITIAL_RECIPES);

    const toggleLike = (id) => {
        setRecipes(prev => prev.map(recipe => {
            if (recipe.id === id) {
                return {
                    ...recipe,
                    likes: recipe.isLiked ? recipe.likes - 1 : recipe.likes + 1,
                    isLiked: !recipe.isLiked
                };
            }
            return recipe;
        }));
    };

    const addRecipe = (newRecipe) => {
        setRecipes(prev => [{ ...newRecipe, author: "You", isLiked: false, likes: 0 }, ...prev]);
    };

    const getRecipeById = (id) => {
        return recipes.find(r => r.id === parseInt(id)); // Ensure ID type match
    };

    const deleteRecipe = (id) => {
        setRecipes(prev => prev.filter(r => r.id !== id));
    };

    const updateRecipe = (id, updatedData) => {
        setRecipes(prev => prev.map(r => r.id === id ? { ...r, ...updatedData } : r));
    };

    return (
        <RecipeContext.Provider value={{ recipes, toggleLike, addRecipe, getRecipeById, deleteRecipe, updateRecipe }}>
            {children}
        </RecipeContext.Provider>
    );
}

export function useRecipes() {
    return useContext(RecipeContext);
}
