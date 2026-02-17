import React, { useState, useEffect } from 'react';
import { X, Plus, Save, Trash2 } from 'lucide-react';

export function AddRecipeModal({ onClose, onAdd, initialData = null }) {
    const [formData, setFormData] = useState({
        title: '',
        description: '',
        introduction: '',
        image: '',
        time: '',
        servings: '',
        category: 'Dinner',
        difficulty: 'Medium',
        isPublic: true,
        ingredients: []
    });

    const [durationInput, setDurationInput] = useState({
        amount: '',
        unit: 'min'
    });

    const [ingredientInput, setIngredientInput] = useState({
        amount: '',
        unit: 'g',
        ingredient: ''
    });

    useEffect(() => {
        if (initialData) {
            setFormData(initialData);
        }
    }, [initialData]);

    const handleAddIngredient = () => {
        if (!ingredientInput.amount || !ingredientInput.ingredient) {
            alert('Bitte Menge und Zutat ausfüllen');
            return;
        }

        setFormData({
            ...formData,
            ingredients: [...formData.ingredients, ingredientInput]
        });

        setIngredientInput({
            amount: '',
            unit: 'g',
            ingredient: ''
        });
    };

    const handleRemoveIngredient = (index) => {
        setFormData({
            ...formData,
            ingredients: formData.ingredients.filter((_, i) => i !== index)
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!formData.title || !formData.image) return;

        if (initialData) {
            onAdd(formData); // This will actually be the update function passed as onAdd
        } else {
            onAdd({
                ...formData,
                likes: 0,
                isLiked: false,
                id: Date.now()
            });
        }
        onClose();
    };

    return (
        <div className="modal fade show d-block" style={{ backgroundColor: 'rgba(0,0,0,0.5)' }} tabIndex="-1">
            <div className="modal-dialog modal-dialog-centered">
                <div className="modal-content animate-fade-in border-0 shadow">
                    <div className="modal-header border-bottom-0 pb-0">
                        <h5 className="modal-title fw-bold">{initialData ? 'Edit Recipe' : 'Add New Recipe'}</h5>
                        <button type="button" className="btn-close" onClick={onClose} aria-label="Close"></button>
                    </div>

                    <div className="modal-body">
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label className="form-label fw-semibold">Title</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="e.g. Creamy Mushroom Pasta"
                                    value={formData.title}
                                    onChange={e => setFormData({ ...formData, title: e.target.value })}
                                    required
                                />
                            </div>

                            <div className="mb-3">
                                <label className="form-label fw-semibold">Image URL</label>
                                <input
                                    type="url"
                                    className="form-control"
                                    placeholder="https://images.unsplash.com/..."
                                    value={formData.image}
                                    onChange={e => setFormData({ ...formData, image: e.target.value })}
                                    required
                                />
                            </div>

                            <div className="row g-3 mb-3">
                                <div className="col-7">
                                    <label className="form-label fw-semibold">Time</label>
                                    <div className="row g-2">
                                        <div className="col-7">
                                            <input
                                                type="number"
                                                min="0"
                                                className="form-control"
                                                placeholder="e.g. 30"
                                                value={durationInput.amount}
                                                onChange={e => setDurationInput({ ...durationInput, amount: e.target.value })}
                                            />
                                        </div>
                                        <div className="col-5">
                                            <select
                                                className="form-select"
                                                value={durationInput.unit}
                                                onChange={e => setDurationInput({ ...durationInput, unit: e.target.value })}
                                            >
                                                <option value="min">min</option>
                                                <option value="h">h</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-5">
                                    <label className="form-label fw-semibold">Servings</label>
                                    <input
                                        type="number"
                                        min="1"
                                        className="form-control"
                                        placeholder="e.g. 2"
                                        value={formData.servings}
                                        onChange={e => setFormData({ ...formData, servings: e.target.value })}
                                    />
                                </div>
                            </div>

                            <div className="mb-3">
                                <label className="form-label fw-semibold">Introduction</label>
                                <textarea
                                    className="form-control"
                                    rows="2"
                                    placeholder="Catchy hook about the recipe..."
                                    value={formData.introduction}
                                    onChange={e => setFormData({ ...formData, introduction: e.target.value })}
                                />
                            </div>

                            <div className="mb-3">
                                <label className="form-label fw-semibold">Zutaten</label>
                                <div className="row g-2 mb-2">
                                    <div className="col-4">
                                        <input
                                            type="number"
                                            className="form-control"
                                            placeholder="Menge"
                                            value={ingredientInput.amount}
                                            onChange={e => setIngredientInput({ ...ingredientInput, amount: e.target.value })}
                                        />
                                    </div>
                                    <div className="col-3">
                                        <select
                                            className="form-select"
                                            value={ingredientInput.unit}
                                            onChange={e => setIngredientInput({ ...ingredientInput, unit: e.target.value })}
                                        >
                                            <option value="g">g</option>
                                            <option value="ml">ml</option>
                                            <option value="TL">TL</option>
                                            <option value="EL">EL</option>
                                            <option value="Stück">Stück</option>
                                            <option value="Prise">Prise</option>
                                        </select>
                                    </div>
                                    <div className="col-5">
                                        <input
                                            type="text"
                                            className="form-control"
                                            placeholder="z.B. Nudeln"
                                            value={ingredientInput.ingredient}
                                            onChange={e => setIngredientInput({ ...ingredientInput, ingredient: e.target.value })}
                                        />
                                    </div>

                                </div>
                                <div className="col-12 mb-2">
                                    <button
                                        type="button"
                                        className="btn btn-success w-100 d-flex align-items-center justify-content-center"
                                        onClick={handleAddIngredient}
                                    >
                                        <Plus size={18} />
                                    </button>
                                </div>
                                {formData.ingredients.length > 0 && (
                                    <div className="list-group">
                                        {formData.ingredients.map((ingredient, index) => (
                                            <div key={index} className="list-group-item d-flex justify-content-between align-items-center">
                                                <span>
                                                    <strong>{ingredient.amount}</strong> {ingredient.unit} {ingredient.ingredient}
                                                </span>
                                                <button
                                                    type="button"
                                                    className="btn btn-sm btn-danger d-flex align-items-center justify-content-center"
                                                    onClick={() => handleRemoveIngredient(index)}
                                                >
                                                    <Trash2 size={16} />
                                                </button>
                                            </div>
                                        ))}
                                    </div>
                                )}
                            </div>

                            <div className="row g-3 mb-3">
                                <div className="col-6">
                                    <label className="form-label fw-semibold">Category</label>
                                    <select
                                        className="form-select"
                                        value={formData.category}
                                        onChange={e => setFormData({ ...formData, category: e.target.value })}
                                    >
                                        <option value="Breakfast">Breakfast</option>
                                        <option value="Lunch">Lunch</option>
                                        <option value="Dinner">Dinner</option>
                                        <option value="Dessert">Dessert</option>
                                        <option value="Snack">Snack</option>
                                    </select>
                                </div>
                                <div className="col-6">
                                    <label className="form-label fw-semibold">Difficulty</label>
                                    <select
                                        className="form-select"
                                        value={formData.difficulty}
                                        onChange={e => setFormData({ ...formData, difficulty: e.target.value })}
                                    >
                                        <option value="Easy">Easy</option>
                                        <option value="Medium">Medium</option>
                                        <option value="Hard">Hard</option>
                                    </select>
                                </div>
                            </div>

                            <div className="mb-3">
                                <div className="d-flex align-items-center justify-content-between p-3 bg-body-secondary rounded">
                                    <div>
                                        <div className="fw-semibold">Recipe Visibility</div>
                                        <div className="text-muted small">
                                            {formData.isPublic ? 'Public - Anyone can see this recipe' : 'Private - Only you can see this recipe'}
                                        </div>
                                    </div>
                                    <div className="form-check form-switch mb-0">
                                        <input
                                            className="form-check-input"
                                            type="checkbox"
                                            role="switch"
                                            id="publicSwitch"
                                            checked={formData.isPublic}
                                            onChange={e => setFormData({ ...formData, isPublic: e.target.checked })}
                                        />
                                        <label className="form-check-label ms-2" htmlFor="publicSwitch">
                                            {formData.isPublic ? 'Public' : 'Private'}
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div className="mb-4">
                                <label className="form-label fw-semibold">Description</label>
                                <textarea
                                    className="form-control"
                                    rows="3"
                                    placeholder="Detailed steps and description..."
                                    value={formData.description}
                                    onChange={e => setFormData({ ...formData, description: e.target.value })}
                                />
                            </div>

                            <button type="submit" className="btn btn-primary w-100 d-flex align-items-center justify-content-center gap-2 py-2">
                                {initialData ? <Save size={20} /> : <Plus size={20} />}
                                {initialData ? 'Save Changes' : 'Share Recipe'}
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}
